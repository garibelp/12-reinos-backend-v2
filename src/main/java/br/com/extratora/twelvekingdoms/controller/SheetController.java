package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.SheetListResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@Validated
public interface SheetController {
    ResponseEntity<SheetModel> create(UserDetailsImpl user, @Valid CreateSheetRequest request);

    ResponseEntity<SheetModel> details(UserDetailsImpl user, UUID id);

    ResponseEntity<SheetListResponse> list(
            @Min(0) int currentPage,
            @Min(1) int pageSize,
            Sort.Direction sortDirection,
            SheetSortEnum sortField,
            UserDetailsImpl user
    );

    ResponseEntity<MessageResponse> delete(UserDetailsImpl user, UUID id);
}
