package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.PlayerListResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface PlayerController {
    ResponseEntity<MessageResponse> delete(Authentication authentication, UUID id);

    ResponseEntity<PlayerModel> playerDetails(Authentication authentication, UUID id);

    ResponseEntity<PlayerListResponse> list(
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            PlayerSortEnum sortField
    );
}
