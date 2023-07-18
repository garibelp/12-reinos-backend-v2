package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.WoundController;
import br.com.extratora.twelvekingdoms.dto.response.WoundListResponse;
import br.com.extratora.twelvekingdoms.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wounds")
public class WoundControllerImpl implements WoundController {
    private final WoundService woundService;

    public WoundControllerImpl(WoundService woundService) {
        this.woundService = woundService;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<WoundListResponse> list() {
        return ResponseEntity.ok(new WoundListResponse(woundService.list()));
    }
}
