package br.com.extratora.twelvekingdoms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String name;
    private String description;
}
