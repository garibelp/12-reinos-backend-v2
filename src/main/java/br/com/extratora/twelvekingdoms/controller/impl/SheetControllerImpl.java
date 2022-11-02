package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.SheetController;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.SheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @Valid @RequestBody CreateSheetRequest request
    ) {
        return ResponseEntity.ok(sheetService.createSheet(user, request));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SheetModel> details(@AuthenticationPrincipal UserDetailsImpl user, @Valid @PathVariable UUID id) {
        return ResponseEntity.ok(sheetService.getSheet(user, id));
    }
}
