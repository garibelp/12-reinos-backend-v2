package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.SheetService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.extratora.twelvekingdoms.enums.DiceEnum.*;
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CREATION_DICES;

@Service
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;

    public SheetServiceImpl(SheetRepository sheetRepository) {
        this.sheetRepository = sheetRepository;
    }

    @Override
    public SheetModel createSheet(UserDetailsImpl user, CreateSheetRequest request) {
        validateDiceAttributes(request);

        var player = new PlayerModel();
        player.setId(user.getId());

        var sheet = new SheetModel();
        sheet.setPlayer(player);
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
        Optional<SheetModel> sheetOpt = sheetRepository.findById(id);

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
}
