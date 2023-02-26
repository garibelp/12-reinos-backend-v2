package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.ForbiddenException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import br.com.extratora.twelvekingdoms.repository.CampaignRepository;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.impl.CampaignServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CAMPAIGN_SHEET_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CampaignServiceTests {

    private final UserDetailsImpl ADMIN_USER = getUserDetailsAdmin();
    private final UserDetailsImpl GM_USER = getUserDetailsGm();
    @Captor
    private ArgumentCaptor<CampaignModel> campaignCaptor;
    @Captor
    private ArgumentCaptor<PageRequest> pageRequestCaptor;
    @Captor
    private ArgumentCaptor<UUID> idCaptor;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private SheetRepository sheetRepository;
    @InjectMocks
    private CampaignServiceImpl campaignService;

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void givenCreateCampaign_whenExistingSheetsDataAndValidRoles_thenShouldSaveData(boolean isAdmin) {
        var campaign = getValidCreateCampaignRequest();
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(Long.valueOf(campaign.getSheetList().size()));

        campaignService.createCampaign(isAdmin ? ADMIN_USER : GM_USER, campaign);

        verify(campaignRepository, times(1)).save(campaignCaptor.capture());
        var model = campaignCaptor.getValue();
        assertEquals(campaign.getName(), model.getName());
        assertEquals(campaign.getNotes(), model.getNotes());
        assertEquals(ADMIN_USER.getId(), model.getPlayer().getId());
        assertEquals(campaign.getSheetList().size(), model.getSheets().size());
    }

    @Test
    void givenCreateCampaign_whenNonExistingSheetsData_thenShouldThrowException() {
        var campaign = getValidCreateCampaignRequest();
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(0L);

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> campaignService.createCampaign(ADMIN_USER, campaign)
        );

        verify(campaignRepository, times(0)).save(any());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getName(), thrownExc.getName());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getDescription(), thrownExc.getDescription());
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void givenAddSheetsToCampaign_whenValidData_thenShouldSaveData(boolean isAdmin) {
        var campaign = CampaignModel.builder().sheets(new HashSet<>()).build();
        var sheetList = getValidIdListRequest();
        if (isAdmin) {
            when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.of(campaign));
        } else {
            when(campaignRepository.findActiveByIdAndPlayerIdEager(any(), any())).thenReturn(Optional.of(campaign));
        }
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(Long.valueOf(sheetList.getIdList().size()));

        campaignService.addSheetsToCampaign(isAdmin ? ADMIN_USER : GM_USER, UUID_1, sheetList);

        verify(campaignRepository, times(1)).save(any());
        verify(campaignRepository, times(isAdmin ? 1 : 0)).findActiveByIdEager(any());
        verify(campaignRepository, times(isAdmin ? 0 : 1)).findActiveByIdAndPlayerIdEager(any(), any());
    }

    @Test
    void givenAddSheetsToCampaign_whenInvalidCampaignId_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.addSheetsToCampaign(ADMIN_USER, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenAddSheetsToCampaign_whenCampaignFromPlayerNotFound_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdAndPlayerIdEager(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                ForbiddenException.class,
                () -> campaignService.addSheetsToCampaign(GM_USER, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenAddSheetsToCampaign_whenInvalidSheetsIds_thenShouldThrowException() {
        var campaign = CampaignModel.builder().sheets(new HashSet<>()).build();
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.of(campaign));
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(0L);

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> campaignService.addSheetsToCampaign(ADMIN_USER, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getName(), thrownExc.getName());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenRemoveSheetsFromCampaign_whenValidData_thenShouldSaveData() {
        var campaign = CampaignModel.builder().sheets(new HashSet<>()).build();
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.of(campaign));

        campaignService.removeSheetsFromCampaign(ADMIN_USER, UUID_1, sheetList);

        verify(campaignRepository, times(1)).save(any());
    }

    @Test
    void givenRemoveSheetsFromCampaign_whenInvalidCampaignId_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.removeSheetsFromCampaign(ADMIN_USER, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenRemoveSheetsFromCampaign_whenCampaignFromPlayerNotFound_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdAndPlayerIdEager(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                ForbiddenException.class,
                () -> campaignService.removeSheetsFromCampaign(GM_USER, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteCampaign_whenValidData_thenShouldSaveData() {
        var campaign = CampaignModel.builder().isActive(true).sheets(new HashSet<>()).build();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.of(campaign));

        campaignService.deleteCampaign(ADMIN_USER, UUID_1);

        verify(campaignRepository, times(1)).save(campaignCaptor.capture());
        assertFalse(campaignCaptor.getValue().isActive());
    }

    @Test
    void givenDeleteCampaign_whenInvalidData_thenShouldThrowException() {
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.deleteCampaign(ADMIN_USER, UUID_1)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteCampaign_whenCampaignNotFromPlayer_thenShouldThrowException() {
        when(campaignRepository.findActiveByIdAndPlayerIdEager(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                ForbiddenException.class,
                () -> campaignService.deleteCampaign(GM_USER, UUID_1)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void givenCampaignsPaginated_whenCalled_thenShouldFetchData(boolean isAdmin) {
        var user = isAdmin ? ADMIN_USER : GM_USER;

        campaignService.campaignsPaginated(user, 1, 2);

        if (isAdmin) {
            verify(campaignRepository, times(1)).findActiveCampaignsPaginated(
                    pageRequestCaptor.capture()
            );
            verify(campaignRepository, times(0)).findActiveCampaignsByPlayerPaginated(any(), any());
        } else {
            verify(campaignRepository, times(1)).findActiveCampaignsByPlayerPaginated(
                    pageRequestCaptor.capture(),
                    idCaptor.capture()
            );
            assertEquals(user.getId(), idCaptor.getValue());
            verify(campaignRepository, times(0)).findActiveCampaignsPaginated(any());
        }
        var page = pageRequestCaptor.getValue();
        assertEquals(2, page.getPageSize());
        assertEquals(1, page.getPageNumber());
    }

    @Test
    void givenCampaignDetails_whenCalledAndAdmin_thenShouldFetchData() {
        when(campaignRepository.existsByIdAndPlayerId(any(), any())).thenReturn(true);

        campaignService.campaignDetails(ADMIN_USER, UUID_1);

        verify(campaignRepository, times(0)).existsByIdAndPlayerId(any(), any());
        verify(sheetRepository, times(1)).findCampaignSheetByCampaignId(
                idCaptor.capture()
        );
        assertEquals(UUID_1, idCaptor.getValue());
    }

    @Test
    void givenCampaignDetails_whenNotFoundAndGm_thenShouldThrowException() {
        when(campaignRepository.existsByIdAndPlayerId(any(), any())).thenReturn(false);

        assertThrows(
                ForbiddenException.class,
                () -> campaignService.campaignDetails(GM_USER, UUID_1)
        );

        verify(sheetRepository, times(0)).findCampaignSheetByCampaignId(any());
    }

    @Test
    void givenCampaignDetails_whenCalledAndGm_thenShouldFetchData() {
        when(campaignRepository.existsByIdAndPlayerId(any(), any())).thenReturn(true);

        campaignService.campaignDetails(GM_USER, UUID_1);

        verify(campaignRepository, times(1)).existsByIdAndPlayerId(any(), any());
        verify(sheetRepository, times(1)).findCampaignSheetByCampaignId(any());
    }
}
