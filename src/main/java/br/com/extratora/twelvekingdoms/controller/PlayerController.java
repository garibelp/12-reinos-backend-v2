package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.PlayerListResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.UUID;

@Validated

public interface PlayerController {
    ResponseEntity<MessageResponse> delete(UserDetailsImpl user, UUID id);

    ResponseEntity<PlayerModel> details(UserDetailsImpl user, UUID id);

    ResponseEntity<PlayerListResponse> list(
            @Min(0) int currentPage,
            @Min(1) int pageSize,
            Sort.Direction sortDirection,
            PlayerSortEnum sortField
    );
}
