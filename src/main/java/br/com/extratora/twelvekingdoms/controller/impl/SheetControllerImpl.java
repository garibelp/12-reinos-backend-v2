package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.SheetController;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.SheetListResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.SheetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sheets")
public class SheetControllerImpl implements SheetController {

    private final SheetService sheetService;

    public SheetControllerImpl(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SheetModel> create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody CreateSheetRequest request
    ) {
        return ResponseEntity.ok(sheetService.createSheet(user, request));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SheetModel> details(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(sheetService.getSheet(user, id));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SheetListResponse> list(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = false) Sort.Direction sortDirection,
            @RequestParam(required = false) SheetSortEnum sortField,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        Page<BasicSheetDto> sheetList = sheetService.sheetsPaginated(user, currentPage, pageSize, sortDirection, sortField);
        return ResponseEntity.ok(
                new SheetListResponse(
                        sheetList.getTotalElements(),
                        sheetList.getTotalPages(),
                        currentPage,
                        pageSize,
                        sheetList.stream().toList()
                )
        );
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable UUID id) {
        sheetService.deleteSheet(id, user);
        return ResponseEntity.accepted().body(new MessageResponse("Sheet deleted successfully!"));
    }
}
