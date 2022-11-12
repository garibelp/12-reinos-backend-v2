package br.com.extratora.twelvekingdoms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicSheetDto {
    private UUID id;
    private String name;
    private int level;
    private boolean isActive;
    private UUID userId;
    private String userName;
}
