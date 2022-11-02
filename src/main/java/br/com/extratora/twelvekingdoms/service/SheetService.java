package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;

import java.util.UUID;

public interface SheetService {
    SheetModel createSheet(UserDetailsImpl user, CreateSheetRequest request);

    SheetModel getSheet(UserDetailsImpl user, UUID id);
}
