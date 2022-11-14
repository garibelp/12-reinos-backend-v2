package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.AptitudeModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.BackgroundRepository;
import br.com.extratora.twelvekingdoms.repository.JobRepository;
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

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

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
    @Mock
    private BackgroundRepository backgroundRepository;
    @Mock
    private JobRepository jobRepository;
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
        verify(lineageRepository, times(0)).existsById(any());
        verify(backgroundRepository, times(0)).findById(any());
        verify(jobRepository, times(0)).findByIdEager(any());
    }

    @Test
    void givenCreateSheet_whenLineageNotFoundOnDb_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        when(lineageRepository.existsById(any())).thenReturn(false);

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getName(), ex.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getDescription(), ex.getError().getDescription());
        verify(sheetRepository, times(0)).save(any());
        verify(lineageRepository, times(1)).existsById(any());
        verify(backgroundRepository, times(0)).findById(any());
        verify(jobRepository, times(0)).findByIdEager(any());
    }

    @Test
    void givenCreateSheet_whenBackgroundNotFoundOnDb_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        when(lineageRepository.existsById(any())).thenReturn(true);
        when(backgroundRepository.findById(any())).thenReturn(Optional.empty());

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_BACKGROUND.getName(), ex.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_BACKGROUND.getDescription(), ex.getError().getDescription());
        verify(sheetRepository, times(0)).save(any());
        verify(lineageRepository, times(1)).existsById(any());
        verify(backgroundRepository, times(1)).findById(any());
        verify(jobRepository, times(0)).findByIdEager(any());
    }

    @Test
    void givenCreateSheet_whenJobNotFoundOnDb_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        when(lineageRepository.existsById(any())).thenReturn(true);
        when(backgroundRepository.findById(any())).thenReturn(Optional.of(getBackgroundModel()));
        when(jobRepository.findByIdEager(any())).thenReturn(Optional.empty());

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_JOB.getName(), ex.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_JOB.getDescription(), ex.getError().getDescription());
        verify(sheetRepository, times(0)).save(any());
        verify(lineageRepository, times(1)).existsById(any());
        verify(backgroundRepository, times(1)).findById(any());
        verify(jobRepository, times(1)).findByIdEager(any());
    }

    @Test
    void givenCreateSheet_whenInvalidAptitudes_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        var background = getBackgroundModel();
        var job = getJobModel();

        when(lineageRepository.existsById(any())).thenReturn(true);
        when(backgroundRepository.findById(any())).thenReturn(Optional.of(background));
        when(jobRepository.findByIdEager(any())).thenReturn(Optional.of(job));

        sheet.setAptitudeList(Arrays.asList(UUID_1));
        InvalidDataException ex1 = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        sheet.setAptitudeList(Arrays.asList(UUID_1, UUID_2, UUID_2));
        InvalidDataException ex2 = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        sheet.setAptitudeList(getValidAptitudeIdList());
        job.setAptitudes(Set.of(
                AptitudeModel.builder().id(UUID_1).build(),
                AptitudeModel.builder().id(UUID_2).build()
        ));
        InvalidDataException ex3 = assertThrows(
                InvalidDataException.class,
                () -> sheetService.createSheet(user, sheet)
        );

        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getName(), ex1.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getDescription(), ex1.getError().getDescription());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getName(), ex2.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getDescription(), ex2.getError().getDescription());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_JOB.getName(), ex3.getError().getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_JOB.getDescription(), ex3.getError().getDescription());

    }

    @Test
    void givenCreateSheet_whenValidPayload_thenShouldSaveData() {
        var user = getUserDetailsUser();
        var sheet = getValidCreateSheetRequest();
        var background = getBackgroundModel();
        var job = getJobModel();
        int expectedPhysical = background.getPhysicalPoints() + job.getPhysicalPoints();
        int expectedMental = background.getMentalPoints() + job.getMentalPoints();

        when(lineageRepository.existsById(any())).thenReturn(true);
        when(backgroundRepository.findById(any())).thenReturn(Optional.of(background));
        when(jobRepository.findByIdEager(any())).thenReturn(Optional.of(job));
        ArgumentCaptor<SheetModel> captor = ArgumentCaptor.forClass(SheetModel.class);

        sheetService.createSheet(user, sheet);

        verify(sheetRepository, times(1)).save(captor.capture());
        assertEquals(user.getId(), captor.getValue().getPlayer().getId());
        assertEquals(expectedPhysical, captor.getValue().getPhysicalCurrent());
        assertEquals(expectedPhysical, captor.getValue().getPhysicalTotal());
        assertEquals(expectedMental, captor.getValue().getMentalCurrent());
        assertEquals(expectedMental, captor.getValue().getMentalTotal());
        verify(lineageRepository, times(1)).existsById(any());
        verify(backgroundRepository, times(1)).findById(any());
        verify(jobRepository, times(1)).findByIdEager(any());
    }

    @Test
    void givenGetSheet_whenSheetNotFoundAndUserNotAdmin_thenShouldThrowUnauthorizedException() {
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.empty());
        var user = getUserDetailsUser();

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFoundAndUserAdmin_thenShouldThrowDataNotFoundException() {
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.empty());
        var user = getUserDetailsAdmin();

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserNotAdmin_thenShouldThrowUnauthorizedException() {
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsUser();

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserAdmin_thenShouldReturnSheet() {
        var expectedSheet = getSheetModel(UUID_2);
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(getUserDetailsAdmin(), UUID_2);

        assertEquals(expectedSheet, receivedSheet);
    }

    @Test
    void givenGetSheet_whenSheetFromUser_thenShouldReturnSheet() {
        var user = getUserDetailsUser();
        var expectedSheet = getSheetModel(user.getId());
        when(sheetRepository.findByIdEager(user.getId())).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(user, user.getId());

        assertEquals(expectedSheet, receivedSheet);
    }

    @Test
    void givenDeleteSheet_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsUser();
        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.deleteSheet(UUID_2, user)
        );
        verify(sheetRepository, times(1)).findByIdEager(UUID_2);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenUserAdminAndDifferentUUIDs_thenShouldDeleteSheet() {
        when(sheetRepository.findByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsAdmin();

        sheetService.deleteSheet(UUID_2, user);

        verify(sheetRepository, times(1)).findByIdEager(UUID_2);
        verify(sheetRepository, times(1)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserNotAdmin_thenThrowUnauthorizedException() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                UnauthorizedException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserAdmin_thenThrowDataNotFoundException() {
        var user = getUserDetailsAdmin();
        var userId = user.getId();
        when(sheetRepository.findByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetFoundOnDbAndUserNotAdminAndOwner_thenShouldDeleteSheet() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findByIdEager(any())).thenReturn(Optional.of(getSheetModel(userId)));

        sheetService.deleteSheet(userId, user);

        verify(sheetRepository, times(1)).findByIdEager(userId);
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
