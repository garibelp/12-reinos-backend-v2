package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdNameListResponse {
    private List<IdNameDto> list = new ArrayList<>();
}
