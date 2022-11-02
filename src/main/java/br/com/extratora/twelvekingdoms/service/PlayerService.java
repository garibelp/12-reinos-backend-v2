package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface PlayerService {
    void deletePlayer(UUID id, UserDetailsImpl user);

    PlayerModel getPlayer(UUID id, UserDetailsImpl user);

    Page<BasicPlayerDto> playersPaginated(int currentPage, int pageSize, Sort.Direction sortDirection, PlayerSortEnum sortField);
}
