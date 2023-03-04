package br.com.extratora.twelvekingdoms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignSheetDto {
    private UUID id;
    private String name;
    private int level;
    private int mentalCurrent;
    private int mentalTotal;
    private int physicalCurrent;
    private int physicalTotal;
    private String lineage;
    private String background;
}
