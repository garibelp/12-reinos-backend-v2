package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.LineageController;
import br.com.extratora.twelvekingdoms.dto.response.LineageListResponse;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import br.com.extratora.twelvekingdoms.service.LineageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/lineages")
public class LineageControllerImpl implements LineageController {
    private final LineageService lineageService;

    public LineageControllerImpl(LineageService lineageService) {
        this.lineageService = lineageService;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<LineageListResponse> list() {
        return ResponseEntity.ok(new LineageListResponse(lineageService.lineageList()));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<LineageModel> details(@RequestParam UUID id) {
        return ResponseEntity.ok(lineageService.getLineage(id));
    }
}
