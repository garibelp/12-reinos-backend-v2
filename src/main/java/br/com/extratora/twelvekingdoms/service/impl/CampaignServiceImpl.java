package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.request.IdListRequest;
import br.com.extratora.twelvekingdoms.dto.response.CampaignDetailsResponse;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CAMPAIGN_SHEET_LIST;

@Service
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
        Optional<CampaignModel> modelOpt = campaignRepository.findActiveById(campaignId);
        if (modelOpt.isEmpty()) {
            throw new DataNotFoundException();
        }
        validateSheetListId(request.getIdList());

        var model = modelOpt.get();
        request.getIdList().forEach(id -> {
            var sheet = new SheetModel();
            sheet.setId(id);
            model.addSheet(sheet);
        });

        campaignRepository.save(model);
    }

    @Override
    public void removeSheetsFromCampaign(UserDetailsImpl user, UUID campaignId, IdListRequest request) {
        Optional<CampaignModel> modelOpt = campaignRepository.findActiveByIdEager(campaignId);
        if (modelOpt.isEmpty()) {
            throw new DataNotFoundException();
        }
        var model = modelOpt.get();
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
        Optional<CampaignModel> modelOpt = campaignRepository.findActiveByIdEager(campaignId);
        if (modelOpt.isEmpty()) {
            throw new DataNotFoundException();
        }
        var model = modelOpt.get();

        model.setActive(false);
        model.getSheets().forEach(model::removeSheet);

        campaignRepository.save(model);
    }

    @Override
    public Page<BasicCampaignDto> campaignsPaginated(UserDetailsImpl user, int currentPage, int pageSize) {
        return campaignRepository.findActiveCampaignsPaginated(
                PageRequest.of(currentPage, pageSize),
                user.getId()
        );
    }

    @Override
    public CampaignDetailsResponse campaignDetails(UUID campaignId) {
        return new CampaignDetailsResponse(sheetRepository.findCampaignSheetByCampaignId(campaignId));
    }

    private void validateSheetListId(List<UUID> ids) {
        if (sheetRepository.countActiveByIdIn(ids) != ids.size()) {
            throw new InvalidDataException(INVALID_CAMPAIGN_SHEET_LIST);
        }
    }
}
