package br.com.extratora.twelvekingdoms.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class IdListRequest {
    @NotEmpty
    private List<UUID> idList = new ArrayList<>();
}
