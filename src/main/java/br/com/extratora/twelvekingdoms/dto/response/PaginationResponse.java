package br.com.extratora.twelvekingdoms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
