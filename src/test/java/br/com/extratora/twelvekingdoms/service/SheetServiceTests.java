package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.service.impl.SheetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SheetServiceTests {
    @Mock
    private SheetRepository sheetRepository;
    @InjectMocks
    private SheetServiceImpl sheetService;

    @ParameterizedTest
    @CsvSource({
            "D4,D4,D4,D4",
            "D6,D6,D6,D6",
            "D8,D8,D8,D8",
            "D4,D4,D4,D6",
            "D4,D4,D4,D8",
            "D4,D4,D6,D6",
            "D4,D4,D8,D8",
            "D4,D6,D6,D6",
            "D4,D6,D6,D8",
            "D4,D6,D8,D8",
            "D4,D8,D8,D8",
    })
    void givenCreateSheet_whenInvalidDiceAttribute_thenShouldThrowInvalidDataException(
            DiceEnum celerity,
            DiceEnum tenacity,
            DiceEnum intelligence,
            DiceEnum cunning
    ) {
        var sheet = getValidCreateSheetRequest();
        var user = getUserDetailsUser();

        sheet.setCelerity(celerity);
        sheet.setTenacity(tenacity);
        sheet.setIntelligence(intelligence);
        sheet.setCunning(cunning);

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_DICES.getName(), ex.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_DICES.getDescription(), ex.getError().getDescription());
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenCreateSheet_whenValidPayload_thenShouldSaveData() {
        var user = getUserDetailsUser();
        ArgumentCaptor<SheetModel> captor = ArgumentCaptor.forClass(SheetModel.class);

        sheetService.createSheet(user, getValidCreateSheetRequest());

        verify(sheetRepository, times(1)).save(captor.capture());
        assertEquals(user.getId(), captor.getValue().getPlayer().getId());
    }

    @Test
    void givenGetSheet_whenSheetNotFoundAndUserNotAdmin_thenShouldThrowUnauthorizedException() {
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.empty());
        var user = getUserDetailsUser();

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.getSheet(user, PLAYER_2_UUID)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFoundAndUserAdmin_thenShouldThrowDataNotFoundException() {
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.empty());
        var user = getUserDetailsAdmin();

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.getSheet(user, PLAYER_2_UUID)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserNotAdmin_thenShouldThrowUnauthorizedException() {
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.of(getSheetModel(PLAYER_2_UUID)));
        var user = getUserDetailsUser();

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.getSheet(user, PLAYER_2_UUID)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserAdmin_thenShouldReturnSheet() {
        var expectedSheet = getSheetModel(PLAYER_2_UUID);
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(getUserDetailsAdmin(), PLAYER_2_UUID);

        assertEquals(expectedSheet, receivedSheet);
    }

    @Test
    void givenGetSheet_whenSheetFromUser_thenShouldReturnSheet() {
        var user = getUserDetailsUser();
        var expectedSheet = getSheetModel(user.getId());
        when(sheetRepository.findById(user.getId())).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(user, user.getId());

        assertEquals(expectedSheet, receivedSheet);
    }

}
