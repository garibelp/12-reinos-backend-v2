package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerListResponse extends PaginationResponse {
    private List<BasicPlayerDto> list;

    public PlayerListResponse(long totalElements, int totalPages, int currentPage, int pageSize, List<BasicPlayerDto> list) {
        super(totalElements, totalPages, currentPage, pageSize);
        this.list = list;
    }
}
