package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateDeathRollsRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.enums.Dice;
import br.com.extratora.twelvekingdoms.enums.SheetSort;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.ForbiddenException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.model.*;
import br.com.extratora.twelvekingdoms.repository.*;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.SheetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.extratora.twelvekingdoms.enums.Dice.*;
import static br.com.extratora.twelvekingdoms.enums.Error.*;

@Service
@Transactional
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;
    private final LineageRepository lineageRepository;
    private final BackgroundRepository backgroundRepository;
    private final JobRepository jobRepository;
    private final WoundRepository woundRepository;

    public SheetServiceImpl(
            SheetRepository sheetRepository,
            LineageRepository lineageRepository,
            BackgroundRepository backgroundRepository,
            JobRepository jobRepository,
            WoundRepository woundRepository
    ) {
        this.sheetRepository = sheetRepository;
        this.lineageRepository = lineageRepository;
        this.backgroundRepository = backgroundRepository;
        this.jobRepository = jobRepository;
        this.woundRepository = woundRepository;
    }

    @Override
    public SheetModel createSheet(UserDetailsImpl user, CreateSheetRequest request) {
        validateDiceAttributes(request);

        if (!lineageRepository.existsById(request.getLineageId())) {
            throw new InvalidDataException(INVALID_CREATION_LINEAGE);
        }

        var background = backgroundRepository.findById(request.getBackgroundId())
                .orElseThrow(() -> new InvalidDataException(INVALID_CREATION_BACKGROUND));

        var job = jobRepository.findByIdEager(request.getJobId())
                .orElseThrow(() -> new InvalidDataException(INVALID_CREATION_JOB));

        var aptitudeList = request.getAptitudeList();
        validateAptitudes(job, aptitudeList);

        var player = new PlayerModel();
        player.setId(user.getId());

        var lineage = new LineageModel();
        lineage.setId(request.getLineageId());

        var sheet = new SheetModel();
        sheet.setPlayer(player);
        sheet.setLineage(lineage);
        sheet.setBackground(background);
        sheet.setJob(job);
        aptitudeList.forEach(uuid -> sheet.addAptitude(AptitudeModel.builder().id(uuid).build()));

        // Calculate attributes
        int mental = background.getMentalPoints() + job.getMentalPoints();
        int physical = background.getPhysicalPoints() + job.getPhysicalPoints();
        sheet.setMentalCurrent(mental);
        sheet.setMentalTotal(mental);
        sheet.setPhysicalCurrent(physical);
        sheet.setPhysicalTotal(physical);

        // Set normal fields
        sheet.setName(request.getName());
        sheet.setIntelligence(request.getIntelligence());
        sheet.setCunning(request.getCunning());
        sheet.setTenacity(request.getTenacity());
        sheet.setCelerity(request.getCelerity());
        sheet.setBond(request.getBond());
        sheet.setMotivation(request.getMotivation());
        sheet.setNotes(request.getNotes());

        return sheetRepository.save(sheet);
    }

    @Override
    public SheetModel getSheet(UserDetailsImpl user, UUID id) {
        return validateAndRetrieveSheetForUser(id, user);
    }

    @Override
    public Page<BasicSheetDto> sheetsPaginated(
            UserDetailsImpl user,
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            SheetSort sortField,
            boolean usePlayerProfile,
            String nameFilter
    ) {
        var pageRequest = PageRequest.of(currentPage, pageSize);

        if (sortDirection != null && sortField != null) {
            pageRequest = pageRequest.withSort(sortDirection, sortField.toString().toLowerCase());
        }

        if (user.isAdmin() && !usePlayerProfile) {
            return sheetRepository.findSheetsPaginatedFilterByNameOpt(pageRequest, nameFilter);
        }

        if (user.isGm() && !usePlayerProfile) {
            return sheetRepository.findActiveSheetsPaginatedFilterByNameOpt(pageRequest, nameFilter);
        }

        return sheetRepository.findActivePlayerSheetsPaginatedFilterByNameOpt(pageRequest, user.getId(), nameFilter);
    }

    @Override
    public void deleteSheet(UUID id, UserDetailsImpl user) {
        SheetModel sheet = validateAndRetrieveSheetForUser(id, user);
        sheet.setActive(false);
        sheetRepository.save(sheet);
    }

    @Override
    public void updateCurrentPoints(UserDetailsImpl user, UUID id, UpdateSheetCurrentPointsRequest request) {
        SheetModel sheet = validateAndRetrieveSheetForUser(id, user);

        validateCurrentPointsUpdate(request.getMentalCurrent(), sheet.getMentalTotal(), "mentalCurrent");
        validateCurrentPointsUpdate(request.getPhysicalCurrent(), sheet.getPhysicalTotal(), "physicalCurrent");
        validateCurrentPointsUpdate(request.getHeroismCurrent(), sheet.getHeroismTotal(), "heroismCurrent");

        if (request.getMentalCurrent() != null) sheet.setMentalCurrent(request.getMentalCurrent());
        if (request.getPhysicalCurrent() != null) sheet.setPhysicalCurrent(request.getPhysicalCurrent());
        if (request.getHeroismCurrent() != null) sheet.setHeroismCurrent(request.getHeroismCurrent());

        sheetRepository.save(sheet);
    }

    @Override
    public void levelUp(UUID id, UserDetailsImpl user) {
        SheetModel sheet = validateAndRetrieveSheetForUser(id, user);
        var currentLevel = sheet.getLevel();
        if (currentLevel >= 3) {
            throw new InvalidDataException(INVALID_SHEET_LEVEL_UP);
        }
        sheet.setLevel(sheet.getLevel() + 1);

        var bonusPhysical = sheet.getJob().getPhysicalPerLevel();
        var bonusMental = sheet.getJob().getMentalPerLevel();
        sheet.setPhysicalTotal(sheet.getPhysicalTotal() + bonusPhysical);
        sheet.setMentalTotal(sheet.getMentalTotal() + bonusMental);

        sheetRepository.save(sheet);
    }

    @Override
    public void addWound(UserDetailsImpl user, UUID woundId, UUID sheetId) {
        var sheet = validateAndRetrieveSheetForUser(sheetId, user);

        if (sheet.getWound() != null) {
            throw new InvalidDataException(SHEET_WITH_WOUND);
        }

        var wound = woundRepository.findById(woundId).orElseThrow(() -> new InvalidDataException(INVALID_WOUND_ID));
        wound.addSheet(sheet);
        sheetRepository.save(sheet);
    }

    @Override
    public void removeWound(UserDetailsImpl user, UUID sheetId) {
        var sheet = validateAndRetrieveSheetForUser(sheetId, user);

        var wound = sheet.getWound();
        if (wound == null) {
            throw new InvalidDataException(SHEET_WITHOUT_WOUND);
        }

        wound.removeSheet(sheet);
        sheetRepository.save(sheet);
    }

    @Override
    public void updateDeathRolls(UserDetailsImpl user, UUID sheetId, UpdateDeathRollsRequest req) {
        var sheet = validateAndRetrieveSheetForUser(sheetId, user);

        sheet.setDeathRollBody(req.getDeathRollBody());
        sheet.setDeathRollMind(req.getDeathRollMind());
        sheet.setDeathRollSpirit(req.getDeathRollSpirit());

        sheetRepository.save(sheet);
    }

    private void validateCurrentPointsUpdate(Integer updated, int maxValue, String field) {
        if (updated != null) {
            if (updated > maxValue)
                throw new InvalidDataException(field, "value cannot be greater than maximum value " + maxValue);
            if (updated < 0) throw new InvalidDataException(field, "value cannot be negative");
        }
    }

    private void validateDiceAttributes(CreateSheetRequest request) {
        Map<Dice, Long> diceMap = Stream.of(
                        request.getCelerity(),
                        request.getTenacity(),
                        request.getIntelligence(),
                        request.getCunning())
                .collect(Collectors.groupingBy(
                        diceEnum -> diceEnum, Collectors.counting()
                ));

        if (diceMap.size() != 3 || !(diceMap.get(D4) == 2 && diceMap.get(D6) == 1 && diceMap.get(D8) == 1)) {
            throw new InvalidDataException(INVALID_CREATION_DICES);
        }
    }

    private void validateAptitudes(JobModel job, List<UUID> aptitudeList) {
        var aptitudeListSize = aptitudeList.size();

        if (aptitudeListSize != 3 || aptitudeList.stream().distinct().count() != aptitudeListSize) {
            throw new InvalidDataException(INVALID_CREATION_APTITUDE_LIST);
        }

        var containsAllIds = job.getAptitudes()
                .stream().map(AptitudeModel::getId)
                .collect(Collectors.toSet())
                .containsAll(aptitudeList);

        if (!containsAllIds) {
            throw new InvalidDataException(INVALID_CREATION_APTITUDE_JOB);
        }
    }

    private SheetModel validateAndRetrieveSheetForUser(UUID sheetId, UserDetailsImpl user) {
        var sheetOpt = sheetRepository.findActiveByIdEager(sheetId);

        if (sheetOpt.isEmpty()) {
            if (user.isAdmin()) throw new DataNotFoundException();
            throw new ForbiddenException();
        }

        var sheet = sheetOpt.get();

        if (!(user.isAdmin() || sheet.getPlayer().getId().equals(user.getId()))) {
            throw new ForbiddenException();
        }

        return sheet;
    }
}
