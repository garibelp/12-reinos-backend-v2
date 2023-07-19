package br.com.extratora.twelvekingdoms.dto.request;

import br.com.extratora.twelvekingdoms.enums.DeathRollStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeathRollsRequest {
    @NotNull
    private DeathRollStatus deathRollBody;
    @NotNull
    private DeathRollStatus deathRollMind;
    @NotNull
    private DeathRollStatus deathRollSpirit;
}
