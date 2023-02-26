package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.CampaignController;
import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.request.IdListRequest;
import br.com.extratora.twelvekingdoms.dto.response.CampaignDetailsResponse;
import br.com.extratora.twelvekingdoms.dto.response.CampaignListResponse;
import br.com.extratora.twelvekingdoms.dto.response.IdResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/campaigns")
public class CampaignControllerImpl implements CampaignController {
    private final CampaignService campaignService;

    public CampaignControllerImpl(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @PostMapping
    public ResponseEntity<IdResponse> create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody CreateCampaignRequest request
    ) {
        var campaign = campaignService.createCampaign(user, request);
        return ResponseEntity.ok(new IdResponse(campaign.getId()));
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @PatchMapping("/addSheets/{campaignId}")
    public ResponseEntity<MessageResponse> addSheets(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID campaignId,
            @RequestBody IdListRequest request
    ) {
        campaignService.addSheetsToCampaign(user, campaignId, request);
        return ResponseEntity.ok(new MessageResponse("Sheets successfully added to campaign!"));
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @PatchMapping("/removeSheets/{campaignId}")
    public ResponseEntity<MessageResponse> removeSheets(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID campaignId,
            @RequestBody IdListRequest request
    ) {
        campaignService.removeSheetsFromCampaign(user, campaignId, request);
        return ResponseEntity.ok(new MessageResponse("Sheets successfully removed from campaign!"));
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID id
    ) {
        campaignService.deleteCampaign(user, id);
        return ResponseEntity.ok(new MessageResponse("Campaign successfully deleted!"));
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<CampaignListResponse> list(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int pageSize,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        var campaignList = campaignService.campaignsPaginated(user, currentPage, pageSize);
        return ResponseEntity.ok(
                new CampaignListResponse(
                        campaignList.getTotalElements(),
                        campaignList.getTotalPages(),
                        currentPage,
                        pageSize,
                        campaignList.stream().toList()
                )
        );
    }

    @Override
    @PreAuthorize("hasAnyRole('GM','ADMIN')")
    @GetMapping
    public ResponseEntity<CampaignDetailsResponse> details(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam UUID id
    ) {
        return ResponseEntity.ok(campaignService.campaignDetails(user, id));
    }
}
