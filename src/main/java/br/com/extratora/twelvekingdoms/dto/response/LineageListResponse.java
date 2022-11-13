package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.BasicLineageDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LineageListResponse {
    private List<BasicLineageDto> list = new ArrayList<>();
}
