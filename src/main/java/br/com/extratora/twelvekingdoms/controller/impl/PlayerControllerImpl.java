package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.PlayerController;
import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.PlayerListResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService playerService;

    public PlayerControllerImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<MessageResponse> delete(Authentication authentication, @RequestParam UUID id) {
        playerService.deletePlayer(id, (UserDetailsImpl) authentication.getPrincipal());
        return ResponseEntity.accepted().body(new MessageResponse("User deleted successfully!"));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerModel> playerDetails(Authentication authentication, @PathVariable UUID id) {
        return ResponseEntity.ok(playerService.getPlayer(id, (UserDetailsImpl) authentication.getPrincipal()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PlayerListResponse> list(
            @RequestParam int currentPage,
            @RequestParam int pageSize,
            @RequestParam(required = false) Sort.Direction sortDirection,
            @RequestParam(required = false) PlayerSortEnum sortField
    ) {
        Page<BasicPlayerDto> playerList = playerService.listPlayers(currentPage, pageSize, sortDirection, sortField);
        return ResponseEntity.ok(
                new PlayerListResponse(
                        playerList.getTotalElements(),
                        playerList.getTotalPages(),
                        currentPage,
                        pageSize,
                        playerList.stream().toList()
                )
        );
    }
}
