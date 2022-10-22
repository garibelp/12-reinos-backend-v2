package br.com.extratora.twelvekingdoms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BasicPlayerDto {
    private UUID id;
    private String username;
    private String email;
    private boolean isActive;
}
