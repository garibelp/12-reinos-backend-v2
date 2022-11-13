package br.com.extratora.twelvekingdoms.dto.request;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSheetRequest {
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    private UUID lineageId;

    @NotNull
    private DiceEnum intelligence;

    @NotNull
    private DiceEnum cunning;

    @NotNull
    private DiceEnum tenacity;

    @NotNull
    private DiceEnum celerity;

    @Max(200)
    private String bond;

    @Max(200)
    private String motivation;
}
