package br.com.extratora.twelvekingdoms.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CreateCampaignRequest {
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    private String notes;

    private List<UUID> sheetList = new ArrayList<>();
}
