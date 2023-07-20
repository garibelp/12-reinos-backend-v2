package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.BackgroundController;
import br.com.extratora.twelvekingdoms.dto.response.BackgroundListResponse;
import br.com.extratora.twelvekingdoms.service.BackgroundService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backgrounds")
public class BackgroundControllerImpl implements BackgroundController {
    private final BackgroundService backgroundService;

    public BackgroundControllerImpl(BackgroundService backgroundService) {
        this.backgroundService = backgroundService;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<BackgroundListResponse> backgroundList() {
        return ResponseEntity.ok(new BackgroundListResponse(backgroundService.backgroundList()));
    }
}
