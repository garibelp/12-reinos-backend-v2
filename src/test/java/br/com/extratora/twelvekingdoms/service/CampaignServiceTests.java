package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import br.com.extratora.twelvekingdoms.repository.CampaignRepository;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.impl.CampaignServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private final UserDetailsImpl user = getUserDetailsUser();
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

    @Test
    void givenCreateCampaign_whenExistingSheetsData_thenShouldSaveData() {
        var campaign = getValidCreateCampaignRequest();
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(Long.valueOf(campaign.getSheetList().size()));

        campaignService.createCampaign(user, campaign);

        verify(campaignRepository, times(1)).save(campaignCaptor.capture());
        var model = campaignCaptor.getValue();
        assertEquals(campaign.getName(), model.getName());
        assertEquals(campaign.getNotes(), model.getNotes());
        assertEquals(user.getId(), model.getPlayer().getId());
        assertEquals(campaign.getSheetList().size(), model.getSheets().size());
    }

    @Test
    void givenCreateCampaign_whenNonExistingSheetsData_thenShouldThrowException() {
        var campaign = getValidCreateCampaignRequest();
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(0L);

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> campaignService.createCampaign(user, campaign)
        );

        verify(campaignRepository, times(0)).save(any());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getName(), thrownExc.getName());
        assertEquals(INVALID_CAMPAIGN_SHEET_LIST.getDescription(), thrownExc.getDescription());
    }

    @Test
    void givenAddSheetsToCampaign_whenValidData_thenShouldSaveData() {
        var campaign = CampaignModel.builder().sheets(new HashSet<>()).build();
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveById(any())).thenReturn(Optional.of(campaign));
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(Long.valueOf(sheetList.getIdList().size()));

        campaignService.addSheetsToCampaign(user, UUID_1, sheetList);

        verify(campaignRepository, times(1)).save(any());
    }

    @Test
    void givenAddSheetsToCampaign_whenInvalidCampaignId_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveById(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.addSheetsToCampaign(user, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenAddSheetsToCampaign_whenInvalidSheetsIds_thenShouldThrowException() {
        var campaign = CampaignModel.builder().sheets(new HashSet<>()).build();
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveById(any())).thenReturn(Optional.of(campaign));
        when(sheetRepository.countActiveByIdIn(any())).thenReturn(0L);

        var thrownExc = assertThrows(
                InvalidDataException.class,
                () -> campaignService.addSheetsToCampaign(user, UUID_1, sheetList)
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

        campaignService.removeSheetsFromCampaign(user, UUID_1, sheetList);

        verify(campaignRepository, times(1)).save(any());
    }

    @Test
    void givenRemoveSheetsFromCampaign_whenInvalidCampaignId_thenShouldThrowException() {
        var sheetList = getValidIdListRequest();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.removeSheetsFromCampaign(user, UUID_1, sheetList)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenDeleteCampaign_whenValidData_thenShouldSaveData() {
        var campaign = CampaignModel.builder().isActive(true).sheets(new HashSet<>()).build();
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.of(campaign));

        campaignService.deleteCampaign(getUserDetailsUser(), UUID_1);

        verify(campaignRepository, times(1)).save(campaignCaptor.capture());
        assertFalse(campaignCaptor.getValue().isActive());
    }

    @Test
    void givenDeleteCampaign_whenInvalidData_thenShouldThrowException() {
        when(campaignRepository.findActiveByIdEager(any())).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> campaignService.deleteCampaign(user, UUID_1)
        );

        verify(campaignRepository, times(0)).save(any());
    }

    @Test
    void givenCampaignsPaginated_whenCalled_thenShouldFetchData() {
        campaignService.campaignsPaginated(user, 1, 2);

        verify(campaignRepository, times(1)).findActiveCampaignsPaginated(
                pageRequestCaptor.capture(),
                idCaptor.capture()
        );
        var page = pageRequestCaptor.getValue();
        assertEquals(2, page.getPageSize());
        assertEquals(1, page.getPageNumber());
        assertEquals(user.getId(), idCaptor.getValue());
    }

    @Test
    void givenCampaignDetails_whenCalled_thenShouldFetchData() {
        campaignService.campaignDetails(UUID_1);

        verify(sheetRepository, times(1)).findCampaignSheetByCampaignId(
                idCaptor.capture()
        );
        assertEquals(UUID_1, idCaptor.getValue());
    }
}
