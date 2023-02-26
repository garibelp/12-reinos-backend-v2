package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.request.IdListRequest;
import br.com.extratora.twelvekingdoms.dto.response.CampaignDetailsResponse;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CampaignService {
    CampaignModel createCampaign(UserDetailsImpl user, CreateCampaignRequest request);

    void addSheetsToCampaign(UserDetailsImpl user, UUID campaignId, IdListRequest request);

    void removeSheetsFromCampaign(UserDetailsImpl user, UUID campaignId, IdListRequest request);

    void deleteCampaign(UserDetailsImpl user, UUID campaignId);

    Page<BasicCampaignDto> campaignsPaginated(
            UserDetailsImpl user,
            int currentPage,
            int pageSize
    );

    CampaignDetailsResponse campaignDetails(UserDetailsImpl user, UUID campaignId);
}
