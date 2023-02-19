package br.com.extratora.twelvekingdoms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignSheetDto {
    private String name;
    private int level;
    private int mentalCurrent;
    private int mentalTotal;
    private int physicalCurrent;
    private int physicalTotal;
}
