package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.LineageRepository;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.SheetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.extratora.twelvekingdoms.enums.DiceEnum.*;
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CREATION_DICES;
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CREATION_LINEAGE;

@Service
@Transactional
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;
    private final LineageRepository lineageRepository;

    public SheetServiceImpl(SheetRepository sheetRepository, LineageRepository lineageRepository) {
        this.sheetRepository = sheetRepository;
        this.lineageRepository = lineageRepository;
    }

    @Override
    public SheetModel createSheet(UserDetailsImpl user, CreateSheetRequest request) {
        validateDiceAttributes(request);

        var player = new PlayerModel();
        player.setId(user.getId());

        var lineageOpt = lineageRepository.findByName(request.getLineage());

        if (lineageOpt.isEmpty()) {
            throw new InvalidDataException(INVALID_CREATION_LINEAGE);
        }

        var sheet = new SheetModel();
        sheet.setPlayer(player);
        sheet.setLineage(lineageOpt.get());
        sheet.setName(request.getName());
        sheet.setIntelligence(request.getIntelligence());
        sheet.setCunning(request.getCunning());
        sheet.setTenacity(request.getTenacity());
        sheet.setCelerity(request.getCelerity());
        sheet.setBond(request.getBond());
        sheet.setMotivation(request.getMotivation());

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
            SheetSortEnum sortField
    ) {
        var pageRequest = PageRequest.of(currentPage, pageSize);

        if (sortDirection != null && sortField != null) {
            pageRequest = pageRequest.withSort(sortDirection, sortField.toString().toLowerCase());
        }

        if (user.isAdmin()) {
            return sheetRepository.findSheetsPaginated(pageRequest);
        }

        return sheetRepository.findActivePlayerSheetsPaginated(pageRequest, user.getId());
    }

    @Override
    public void deleteSheet(UUID id, UserDetailsImpl user) {
        SheetModel sheet = validateAndRetrieveSheetForUser(id, user);
        sheet.setActive(false);
        sheetRepository.save(sheet);
    }

    private void validateDiceAttributes(CreateSheetRequest request) {
        Map<DiceEnum, Long> diceMap = Stream.of(
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

    private SheetModel validateAndRetrieveSheetForUser(UUID sheetId, UserDetailsImpl user) {
        Optional<SheetModel> sheetOpt = sheetRepository.findById(sheetId);

        if (sheetOpt.isEmpty()) {
            if (user.isAdmin()) throw new DataNotFoundException();
            throw new UnauthorizedException();
        }

        SheetModel sheet = sheetOpt.get();

        if (!(user.isAdmin() || sheet.getPlayer().getId().equals(user.getId()))) {
            throw new UnauthorizedException();
        }

        return sheet;
    }
}
