package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CampaignListResponse extends PaginationResponse {
    private List<BasicCampaignDto> list;

    public CampaignListResponse(long totalElements, int totalPages, int currentPage, int pageSize, List<BasicCampaignDto> list) {
        super(totalElements, totalPages, currentPage, pageSize);
        this.list = list;
    }
}
