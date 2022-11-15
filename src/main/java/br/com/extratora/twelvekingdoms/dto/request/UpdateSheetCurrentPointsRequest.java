package br.com.extratora.twelvekingdoms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSheetCurrentPointsRequest {
    private Integer mentalCurrent;
    private Integer physicalCurrent;
    private Integer heroismCurrent;
}
