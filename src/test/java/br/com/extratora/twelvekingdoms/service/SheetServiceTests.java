package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import br.com.extratora.twelvekingdoms.enums.RolesEnum;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.ForbiddenException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.model.AptitudeModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.model.WoundsModel;
import br.com.extratora.twelvekingdoms.repository.*;
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
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.*;
import static br.com.extratora.twelvekingdoms.enums.RolesEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SheetServiceTests {
    @Captor
    private ArgumentCaptor<PageRequest> pageRequestCaptor;
    @Captor
    private ArgumentCaptor<SheetModel> sheetCaptor;
    @Mock
    private SheetRepository sheetRepository;
    @Mock
    private LineageRepository lineageRepository;
    @Mock
    private BackgroundRepository backgroundRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private WoundRepository woundRepository;
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

        assertEquals(ErrorEnum.INVALID_CREATION_DICES.getName(), ex.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_DICES.getDescription(), ex.getDescription());
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

        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getName(), ex.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_LINEAGE.getDescription(), ex.getDescription());
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

        assertEquals(ErrorEnum.INVALID_CREATION_BACKGROUND.getName(), ex.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_BACKGROUND.getDescription(), ex.getDescription());
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

        assertEquals(ErrorEnum.INVALID_CREATION_JOB.getName(), ex.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_JOB.getDescription(), ex.getDescription());
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

        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getName(), ex1.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getDescription(), ex1.getDescription());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getName(), ex2.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_LIST.getDescription(), ex2.getDescription());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_JOB.getName(), ex3.getName());
        assertEquals(ErrorEnum.INVALID_CREATION_APTITUDE_JOB.getDescription(), ex3.getDescription());

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
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.empty());
        var user = getUserDetailsUser();

        assertThrows(
                ForbiddenException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFoundAndUserAdmin_thenShouldThrowDataNotFoundException() {
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.empty());
        var user = getUserDetailsAdmin();

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserNotAdmin_thenShouldThrowUnauthorizedException() {
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsUser();

        assertThrows(
                ForbiddenException.class,
                () -> sheetService.getSheet(user, UUID_2)
        );
    }

    @Test
    void givenGetSheet_whenSheetNotFromUserAndUserAdmin_thenShouldReturnSheet() {
        var expectedSheet = getSheetModel(UUID_2);
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(getUserDetailsAdmin(), UUID_2);

        assertEquals(expectedSheet, receivedSheet);
    }

    @Test
    void givenGetSheet_whenSheetFromUser_thenShouldReturnSheet() {
        var user = getUserDetailsUser();
        var expectedSheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(user.getId())).thenReturn(Optional.of(expectedSheet));

        var receivedSheet = sheetService.getSheet(user, user.getId());

        assertEquals(expectedSheet, receivedSheet);
    }

    @Test
    void givenDeleteSheet_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsUser();
        assertThrows(
                ForbiddenException.class,
                () -> sheetService.deleteSheet(UUID_2, user)
        );
        verify(sheetRepository, times(1)).findActiveByIdEager(UUID_2);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenUserAdminAndDifferentUUIDs_thenShouldDeleteSheet() {
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsAdmin();

        sheetService.deleteSheet(UUID_2, user);

        verify(sheetRepository, times(1)).findActiveByIdEager(UUID_2);
        verify(sheetRepository, times(1)).save(sheetCaptor.capture());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserNotAdmin_thenThrowUnauthorizedException() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findActiveByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                ForbiddenException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetNotFoundOnDbAndUserAdmin_thenThrowDataNotFoundException() {
        var user = getUserDetailsAdmin();
        var userId = user.getId();
        when(sheetRepository.findActiveByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.deleteSheet(userId, user)
        );

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteSheet_whenSheetFoundOnDbAndUserNotAdminAndOwner_thenShouldDeleteSheet() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(getSheetModel(userId)));

        sheetService.deleteSheet(userId, user);

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(1)).save(any());
    }

    @ParameterizedTest
    @CsvSource({
            "0,1,,,ROLE_USER,false",
            "0,1,,,ROLE_GM,false",
            "0,1,,,ROLE_ADMIN,false",
            "0,1,ASC,,ROLE_USER,false",
            "0,1,ASC,,ROLE_GM,false",
            "0,1,ASC,,ROLE_ADMIN,false",
            "0,1,,NAME,ROLE_USER,false",
            "0,1,,NAME,ROLE_GM,false",
            "0,1,,NAME,ROLE_ADMIN,false",
            "0,1,ASC,NAME,ROLE_USER,false",
            "0,1,ASC,NAME,ROLE_GM,false",
            "0,1,ASC,NAME,ROLE_ADMIN,false",
            "0,1,DESC,NAME,ROLE_USER,false",
            "0,1,DESC,NAME,ROLE_GM,false",
            "0,1,DESC,NAME,ROLE_ADMIN,false",
            "0,1,ASC,LEVEL,ROLE_USER,false",
            "0,1,ASC,LEVEL,ROLE_GM,false",
            "0,1,ASC,LEVEL,ROLE_ADMIN,false",
            "0,1,DESC,LEVEL,ROLE_USER,false",
            "0,1,DESC,LEVEL,ROLE_GM,false",
            "0,1,DESC,LEVEL,ROLE_ADMIN,false",
            "0,1,,,ROLE_USER,true",
            "0,1,,,ROLE_GM,true",
            "0,1,,,ROLE_ADMIN,true",
            "0,1,ASC,,ROLE_USER,true",
            "0,1,ASC,,ROLE_GM,true",
            "0,1,ASC,,ROLE_ADMIN,true",
            "0,1,,NAME,ROLE_USER,true",
            "0,1,,NAME,ROLE_GM,true",
            "0,1,,NAME,ROLE_ADMIN,true",
            "0,1,ASC,NAME,ROLE_USER,true",
            "0,1,ASC,NAME,ROLE_GM,true",
            "0,1,ASC,NAME,ROLE_ADMIN,true",
            "0,1,DESC,NAME,ROLE_USER,true",
            "0,1,DESC,NAME,ROLE_GM,true",
            "0,1,DESC,NAME,ROLE_ADMIN,true",
            "0,1,ASC,LEVEL,ROLE_USER,true",
            "0,1,ASC,LEVEL,ROLE_GM,true",
            "0,1,ASC,LEVEL,ROLE_ADMIN,true",
            "0,1,DESC,LEVEL,ROLE_USER,true",
            "0,1,DESC,LEVEL,ROLE_GM,true",
            "0,1,DESC,LEVEL,ROLE_ADMIN,true",
    })
    void givenSheetsPaginated_whenValid_thenCallWithPagination(
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            SheetSortEnum sortField,
            RolesEnum profile,
            boolean usePlayerProfile
    ) {
        var user = switch (profile) {
            case ROLE_USER -> getUserDetailsUser();
            case ROLE_GM -> getUserDetailsGm();
            default -> getUserDetailsAdmin();
        };

        sheetService.sheetsPaginated(user, currentPage, pageSize, sortDirection, sortField, usePlayerProfile, null);

        if (ROLE_ADMIN.equals(profile) && !usePlayerProfile) {
            verify(sheetRepository, times(1)).findSheetsPaginatedFilterByNameOpt(pageRequestCaptor.capture(), any());
        }

        if (ROLE_GM.equals(profile) && !usePlayerProfile) {
            verify(sheetRepository, times(1)).findActiveSheetsPaginatedFilterByNameOpt(pageRequestCaptor.capture(), any());
        }

        if (ROLE_USER.equals(profile) || usePlayerProfile) {
            verify(sheetRepository, times(1)).findActivePlayerSheetsPaginatedFilterByNameOpt(pageRequestCaptor.capture(), any(), any());
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

    @ParameterizedTest
    @CsvSource({
            ",,",
            ",,0",
            "1,,",
            ",1,",
            "1,1,",
            "1,,0",
            ",1,0",
            "1,1,0",
    })
    void givenUpdateCurrentPoints_whenValidPayload_thenShouldSaveData(
            Integer mentalCurrent,
            Integer physicalCurrent,
            Integer heroismCurrent
    ) {
        var dbSheet = getSheetModel(UUID_1);
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(dbSheet));
        ArgumentCaptor<SheetModel> captor = ArgumentCaptor.forClass(SheetModel.class);

        sheetService.updateCurrentPoints(
                getUserDetailsAdmin(),
                UUID_1,
                new UpdateSheetCurrentPointsRequest(mentalCurrent, physicalCurrent, heroismCurrent)
        );

        int expectedMental = mentalCurrent != null ? mentalCurrent : dbSheet.getMentalCurrent();
        int expectedPhysical = physicalCurrent != null ? physicalCurrent : dbSheet.getPhysicalCurrent();
        int expectedHeroism = heroismCurrent != null ? heroismCurrent : dbSheet.getHeroismCurrent();

        verify(sheetRepository, times(1)).save(captor.capture());
        assertEquals(expectedMental, captor.getValue().getMentalCurrent());
        assertEquals(expectedPhysical, captor.getValue().getPhysicalCurrent());
        assertEquals(expectedHeroism, captor.getValue().getHeroismCurrent());
    }

    @ParameterizedTest
    @CsvSource({
            "6,,,false",
            "-1,,,true",
            ",10,,false",
            ",-1,,true",
            ",,2,false",
            ",,-1,true",
    })
    void givenUpdateCurrentPoints_whenInvalidPayload_thenShouldThrowInvalidDataException(
            Integer mentalCurrent,
            Integer physicalCurrent,
            Integer heroismCurrent,
            boolean isNegativeValueException
    ) {
        var dbSheet = getSheetModel(UUID_1);
        var user = getUserDetailsAdmin();
        var request = new UpdateSheetCurrentPointsRequest(mentalCurrent, physicalCurrent, heroismCurrent);
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(dbSheet));

        InvalidDataException ex = assertThrows(
                InvalidDataException.class,
                () -> sheetService.updateCurrentPoints(
                        user,
                        UUID_1,
                        request
                )
        );

        if (isNegativeValueException) {
            assertEquals("value cannot be negative", ex.getDescription());
        } else {
            assertTrue(ex.getDescription().contains("value cannot be greater than maximum value "));
        }
    }

    @Test
    void givenLevelUp_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(getSheetModel(UUID_2)));
        var user = getUserDetailsUser();
        assertThrows(
                ForbiddenException.class,
                () -> sheetService.levelUp(UUID_2, user)
        );
        verify(sheetRepository, times(1)).findActiveByIdEager(UUID_2);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenLevelUp_whenUserAdminAndDifferentUUIDs_thenShouldLevelUp() {
        var sheet = getSheetModel(UUID_2);
        var job = sheet.getJob();
        var expectedLevel = sheet.getLevel() + 1;
        var expectedPhysical = sheet.getPhysicalTotal() + job.getPhysicalPerLevel();
        var expectedMental = sheet.getMentalTotal() + job.getMentalPerLevel();
        when(sheetRepository.findActiveByIdEager(UUID_2)).thenReturn(Optional.of(sheet));
        var user = getUserDetailsAdmin();

        sheetService.levelUp(UUID_2, user);

        verify(sheetRepository, times(1)).findActiveByIdEager(UUID_2);
        verify(sheetRepository, times(1)).save(sheetCaptor.capture());
        var savedSheet = sheetCaptor.getValue();
        assertEquals(expectedLevel, savedSheet.getLevel());
        assertEquals(expectedPhysical, savedSheet.getPhysicalTotal());
        assertEquals(expectedMental, savedSheet.getMentalTotal());
    }

    @Test
    void givenLevelUp_whenSheetNotFoundOnDbAndUserNotAdmin_thenThrowUnauthorizedException() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        when(sheetRepository.findActiveByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                ForbiddenException.class,
                () -> sheetService.levelUp(userId, user)
        );

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenLevelUp_whenSheetNotFoundOnDbAndUserAdmin_thenThrowDataNotFoundException() {
        var user = getUserDetailsAdmin();
        var userId = user.getId();
        when(sheetRepository.findActiveByIdEager(userId)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> sheetService.levelUp(userId, user)
        );

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(0)).save(any());
    }

    @Test
    void givenLevelUp_whenSheetFoundOnDbAndUserNotAdminAndOwner_thenShouldShouldLevelUp() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        var sheet = getSheetModel(userId);
        var job = sheet.getJob();
        var expectedLevel = sheet.getLevel() + 1;
        var expectedPhysical = sheet.getPhysicalTotal() + job.getPhysicalPerLevel();
        var expectedMental = sheet.getMentalTotal() + job.getMentalPerLevel();
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        sheetService.levelUp(userId, user);

        verify(sheetRepository, times(1)).findActiveByIdEager(userId);
        verify(sheetRepository, times(1)).save(sheetCaptor.capture());
        var savedSheet = sheetCaptor.getValue();
        assertEquals(expectedLevel, savedSheet.getLevel());
        assertEquals(expectedPhysical, savedSheet.getPhysicalTotal());
        assertEquals(expectedMental, savedSheet.getMentalTotal());
    }

    @Test
    void givenLevelUp_whenSheetOnMaxLevel_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var userId = user.getId();
        var sheet = getSheetModel(user.getId());
        sheet.setLevel(3);
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> sheetService.levelUp(userId, user)
        );

        verify(sheetRepository, times(0)).save(any());
        assertEquals(INVALID_SHEET_LEVEL_UP.getName(), thrownExc.getName());
        assertEquals(INVALID_SHEET_LEVEL_UP.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenAddWound_whenAlreadyHaveWound_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        sheet.setWound(new WoundsModel());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> sheetService.addWound(user, UUID_1, UUID_2)
        );

        verify(sheetRepository, times(0)).save(any());
        assertEquals(SHEET_WITH_WOUND.getName(), thrownExc.getName());
        assertEquals(SHEET_WITH_WOUND.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenAddWound_whenWoundNotFoundOnDb_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));
        when(woundRepository.findById(any())).thenReturn(Optional.empty());

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> sheetService.addWound(user, UUID_1, UUID_2)
        );

        verify(sheetRepository, times(0)).save(any());
        assertEquals(INVALID_WOUND_ID.getName(), thrownExc.getName());
        assertEquals(INVALID_WOUND_ID.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenAddWound_whenValidData_thenShouldSave() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));
        when(woundRepository.findById(any())).thenReturn(Optional.of(new WoundsModel()));

        sheetService.addWound(user, UUID_1, UUID_2);

        verify(sheetRepository, times(1)).save(any());
    }

    @Test
    void givenRemoveWound_whenNoWound_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> sheetService.removeWound(user, UUID_1)
        );

        verify(sheetRepository, times(0)).save(any());
        assertEquals(SHEET_WITHOUT_WOUND.getName(), thrownExc.getName());
        assertEquals(SHEET_WITHOUT_WOUND.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenRemoveWound_whenValidData_thenShouldSave() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        sheet.setWound(new WoundsModel());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        sheetService.removeWound(user, UUID_1);

        verify(sheetRepository, times(1)).save(any());
    }

    @Test
    void givenFailDeathRoll_whenMaxFailures_thenShouldThrowInvalidDataException() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        sheet.setDeathRolls((short) 3);
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> sheetService.failDeathRoll(user, UUID_1)
        );

        verify(sheetRepository, times(0)).save(any());
        assertEquals(SHEET_WITH_ALL_FAILED_ROLLS.getName(), thrownExc.getName());
        assertEquals(SHEET_WITH_ALL_FAILED_ROLLS.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenFailDeathRoll_whenNoMaxFailures_thenShouldSave() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        sheetService.failDeathRoll(user, UUID_1);

        verify(sheetRepository, times(1)).save(any());
    }

    @Test
    void givenResetDeathRoll_whenCalled_thenShouldSave() {
        var user = getUserDetailsUser();
        var sheet = getSheetModel(user.getId());
        when(sheetRepository.findActiveByIdEager(any())).thenReturn(Optional.of(sheet));

        sheetService.resetDeathRoll(user, UUID_1);

        verify(sheetRepository, times(1)).save(any());
    }
}
