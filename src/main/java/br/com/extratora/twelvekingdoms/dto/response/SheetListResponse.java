package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SheetListResponse extends PaginationResponse {
    private List<BasicSheetDto> list;

    public SheetListResponse(long totalElements, int totalPages, int currentPage, int pageSize, List<BasicSheetDto> list) {
        super(totalElements, totalPages, currentPage, pageSize);
        this.list = list;
    }
}
