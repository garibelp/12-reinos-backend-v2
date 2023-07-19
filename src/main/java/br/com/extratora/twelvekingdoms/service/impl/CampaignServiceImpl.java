package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.request.IdListRequest;
import br.com.extratora.twelvekingdoms.dto.response.CampaignDetailsResponse;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.ForbiddenException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.repository.CampaignRepository;
import br.com.extratora.twelvekingdoms.repository.SheetRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static br.com.extratora.twelvekingdoms.enums.Error.INVALID_CAMPAIGN_SHEET_LIST;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;
    private final SheetRepository sheetRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository, SheetRepository sheetRepository) {
        this.campaignRepository = campaignRepository;
        this.sheetRepository = sheetRepository;
    }

    @Override
    public CampaignModel createCampaign(UserDetailsImpl user, CreateCampaignRequest request) {
        var sheetList = request.getSheetList();
        validateSheetListId(sheetList);

        var player = new PlayerModel();
        player.setId(user.getId());

        var model = new CampaignModel();

        model.setPlayer(player);
        model.setName(request.getName());
        model.setNotes(request.getNotes());
        sheetList.forEach(id -> {
            var sheet = new SheetModel();
            sheet.setId(id);
            model.addSheet(sheet);
        });

        return campaignRepository.save(model);
    }

    @Override
    public void addSheetsToCampaign(UserDetailsImpl user, UUID campaignId, IdListRequest request) {
        var model = retrieveCampaignEager(user, campaignId);
        validateSheetListId(request.getIdList());

        request.getIdList().forEach(id -> {
            if (model.getSheets().stream().noneMatch(s -> s.getId().equals(id))) {
                var sheet = new SheetModel();
                sheet.setId(id);
                model.addSheet(sheet);
            }
        });

        campaignRepository.save(model);
    }

    @Override
    public void removeSheetsFromCampaign(UserDetailsImpl user, UUID campaignId, IdListRequest request) {
        var model = retrieveCampaignEager(user, campaignId);
        var sheetList = model.getSheets();

        request.getIdList().forEach(id -> sheetList.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .ifPresent(model::removeSheet)
        );

        campaignRepository.save(model);
    }

    @Override
    public void deleteCampaign(UserDetailsImpl user, UUID campaignId) {
        var model = retrieveCampaignEager(user, campaignId);

        model.setActive(false);
        model.getSheets().forEach(model::removeSheet);

        campaignRepository.save(model);
    }

    @Override
    public Page<BasicCampaignDto> campaignsPaginated(UserDetailsImpl user, int currentPage, int pageSize) {
        if (user.isAdmin()) {
            return campaignRepository.findActiveCampaignsPaginated(PageRequest.of(currentPage, pageSize));
        }
        return campaignRepository.findActiveCampaignsByPlayerPaginated(
                PageRequest.of(currentPage, pageSize),
                user.getId()
        );
    }

    @Override
    public CampaignDetailsResponse campaignDetails(UserDetailsImpl user, UUID campaignId) {
        var campaignOpt = user.isAdmin() ?
                campaignRepository.findActiveById(campaignId) :
                campaignRepository.findActiveByIdAndPlayerId(campaignId, user.getId());

        if (campaignOpt.isEmpty()) {
            if (user.isAdmin()) throw new DataNotFoundException();
            throw new ForbiddenException();
        }

        var campaign = campaignOpt.get();
        var sheets = sheetRepository.findCampaignSheetByCampaignId(campaignId);

        return new CampaignDetailsResponse(campaign.getId(), campaign.getName(), campaign.getNotes(), sheets);
    }

    private CampaignModel retrieveCampaignEager(UserDetailsImpl user, UUID campaignId) {
        var campaignOpt = user.isAdmin() ?
                campaignRepository.findActiveByIdEager(campaignId) :
                campaignRepository.findActiveByIdAndPlayerIdEager(campaignId, user.getId());

        if (campaignOpt.isEmpty()) {
            if (user.isAdmin()) throw new DataNotFoundException();
            throw new ForbiddenException();
        }

        return campaignOpt.get();
    }

    private void validateSheetListId(List<UUID> ids) {
        if (sheetRepository.countActiveByIdIn(ids) != ids.size()) {
            throw new InvalidDataException(INVALID_CAMPAIGN_SHEET_LIST);
        }
    }
}
