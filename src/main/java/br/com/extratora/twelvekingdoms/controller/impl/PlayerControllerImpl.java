package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.PlayerController;
import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.PlayerListResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSort;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable UUID id) {
        playerService.deletePlayer(id, user);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PlayerModel> details(@AuthenticationPrincipal UserDetailsImpl user, @RequestParam UUID id) {
        return ResponseEntity.ok(playerService.getPlayer(id, user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<PlayerListResponse> list(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = false) Sort.Direction sortDirection,
            @RequestParam(required = false) PlayerSort sortField
    ) {
        Page<BasicPlayerDto> playerList = playerService.playersPaginated(currentPage, pageSize, sortDirection, sortField);
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
