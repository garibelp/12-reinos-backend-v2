package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.LineageRepository;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.service.impl.SheetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SheetServiceTests {
    @Captor
    private ArgumentCaptor<PageRequest> pageRequestCaptor;
    @Mock
    private SheetRepository sheetRepository;
    @Mock
    private LineageRepository lineageRepository;
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
        verify(lineageRepository, times(0)).findByName(any());
    }

    @Test
    void givenCreateSheet_whenLineageNotFoundOnDb_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        when(lineageRepository.findByName(any())).thenReturn(Optional.empty());

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getName(), ex.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getDescription(), ex.getError().getDescription());
        verify(sheetRepository, times(0)).save(any());
        verify(lineageRepository, times(1)).findByName(any());
    }

    @Test
    void givenCreateSheet_whenValidPayload_thenShouldSaveData() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        when(lineageRepository.findByName(any())).thenReturn(Optional.ofNullable(getLineageModel(sheet.getLineage())));
        ArgumentCaptor<SheetModel> captor = ArgumentCaptor.forClass(SheetModel.class);

        sheetService.createSheet(user, sheet);

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

    @Test
    void givenDeleteSheet_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.of(getSheetModel(PLAYER_2_UUID)));
        var user = getUserDetailsUser();
        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.deleteSheet(PLAYER_2_UUID, user)
        );
        verify(sheetRepository, times(1)).findById(PLAYER_2_UUID);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenUserAdminAndDifferentUUIDs_thenShouldDeleteSheet() {
        when(sheetRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.of(getSheetModel(PLAYER_2_UUID)));
        var user = getUserDetailsAdmin();

        sheetService.deleteSheet(PLAYER_2_UUID, user);

        verify(sheetRepository, times(1)).findById(PLAYER_2_UUID);
        verify(sheetRepository, times(1)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserNotAdmin_thenThrowUnauthorizedException() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findById(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserAdmin_thenThrowDataNotFoundException() {
        var user = getUserDetailsAdmin();
        var userId = user.getId();
        when(sheetRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findById(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetFoundOnDbAndUserNotAdminAndOwner_thenShouldDeleteSheet() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findById(any())).thenReturn(Optional.of(getSheetModel(userId)));

        sheetService.deleteSheet(userId, user);

        verify(sheetRepository, times(1)).findById(userId);
        verify(sheetRepository, times(1)).save(any());
    }

    @ParameterizedTest
    @CsvSource({
            "0,1,,,true",
            "0,1,,,false",
            "0,1,ASC,,true",
            "0,1,ASC,,false",
            "0,1,,NAME,true",
            "0,1,,NAME,false",
            "0,1,ASC,NAME,true",
            "0,1,ASC,NAME,false",
            "0,1,DESC,NAME,true",
            "0,1,DESC,NAME,false",
            "0,1,ASC,LEVEL,true",
            "0,1,ASC,LEVEL,false",
            "0,1,DESC,LEVEL,true",
            "0,1,DESC,LEVEL,false",
    })
    void givenSheetsPaginated_whenValid_thenCallWithPagination(
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            SheetSortEnum sortField,
            boolean adminUser
    ) {
        var user = adminUser ? getUserDetailsAdmin() : getUserDetailsUser();
        sheetService.sheetsPaginated(user, currentPage, pageSize, sortDirection, sortField);

        if (adminUser) {

            verify(sheetRepository, times(1))
                    .findSheetsPaginated(pageRequestCaptor.capture());
        } else {
            verify(sheetRepository, times(1))
                    .findActivePlayerSheetsPaginated(pageRequestCaptor.capture(), any());
        }

        Pageable page = pageRequestCaptor.getValue();

        assertEquals(currentPage, page.getPageNumber());
        assertEquals(pageSize, page.getPageSize());
        if (sortDirection == null || sortField == null) {
            assertEquals(Sort.unsorted(), page.getSort());
        } else {
            Sort.Order sort = page.getSort().get().findFirst().get();
            assertEquals(sortDirection, sort.getDirection());
            assertEquals(sortField.name(), sort.getProperty().toUpperCase());
        }
    }
}
