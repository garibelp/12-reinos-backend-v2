package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.CampaignSheetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDetailsResponse {
    private UUID id;
    private String name;
    private String notes;
    private Set<CampaignSheetDto> sheets;
}
