package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface SheetController {
    ResponseEntity<SheetModel> create(UserDetailsImpl user, CreateSheetRequest request);

    ResponseEntity<SheetModel> details(UserDetailsImpl user, UUID id);
}
