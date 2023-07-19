package br.com.extratora.twelvekingdoms.dto.request;

import br.com.extratora.twelvekingdoms.enums.Dice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
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
    private UUID backgroundId;

    @NotNull
    private UUID jobId;

    private List<UUID> aptitudeList = new ArrayList<>();

    @NotNull
    private Dice intelligence;

    @NotNull
    private Dice cunning;

    @NotNull
    private Dice tenacity;

    @NotNull
    private Dice celerity;

    @Max(200)
    private String bond;

    @Max(200)
    private String motivation;

    private String notes;
}
