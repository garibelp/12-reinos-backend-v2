package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.SheetController;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateDeathRollsRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.dto.response.IdResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.SheetListResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSort;
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
    public ResponseEntity<IdResponse> create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody CreateSheetRequest request
    ) {
        var sheet = sheetService.createSheet(user, request);
        return ResponseEntity.ok(new IdResponse(sheet.getId()));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SheetModel> details(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam UUID id
    ) {
        return ResponseEntity.ok(sheetService.getSheet(user, id));
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','GM','ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<SheetListResponse> list(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = false) Sort.Direction sortDirection,
            @RequestParam(required = false) SheetSort sortField,
            @RequestParam(defaultValue = "false") boolean usePlayerProfile,
            @RequestParam(defaultValue = "") String nameFilter,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        Page<BasicSheetDto> sheetList = sheetService.sheetsPaginated(
                user,
                currentPage,
                pageSize,
                sortDirection,
                sortField,
                usePlayerProfile,
                nameFilter
        );
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
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable UUID id) {
        sheetService.deleteSheet(id, user);
        return ResponseEntity.ok(new MessageResponse("Sheet deleted successfully!"));
    }

    @Override
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/{id}/currentPoints")
    public ResponseEntity<MessageResponse> updateCurrentPoints(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID id,
            @RequestBody UpdateSheetCurrentPointsRequest request
    ) {
        sheetService.updateCurrentPoints(user, id, request);
        return ResponseEntity.ok(new MessageResponse("Sheet current points updated successfully!"));
    }

    @Override
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/{id}/levelUp")
    public ResponseEntity<MessageResponse> levelUp(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable UUID id) {
        sheetService.levelUp(id, user);
        return ResponseEntity.ok(new MessageResponse("Sheet successfully leveled up!"));
    }

    @Override
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/{sheetId}/addWound")
    public ResponseEntity<MessageResponse> addWound(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam UUID woundId,
            @PathVariable UUID sheetId
    ) {
        sheetService.addWound(user, woundId, sheetId);
        return ResponseEntity.ok(new MessageResponse("Successfully added wound to sheet!"));
    }

    @Override
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/{sheetId}/removeWound")
    public ResponseEntity<MessageResponse> removeWound(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable UUID sheetId) {
        sheetService.removeWound(user, sheetId);
        return ResponseEntity.ok(new MessageResponse("Successfully removed wound to sheet!"));
    }

    @Override
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/{sheetId}/updateDeathRolls")
    public ResponseEntity<MessageResponse> updateDeathRolls(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable UUID sheetId,
            @RequestBody UpdateDeathRollsRequest req
    ) {
        sheetService.updateDeathRolls(user, sheetId, req);
        return ResponseEntity.ok(new MessageResponse("Successfully updated sheet death rolls!"));
    }

}
