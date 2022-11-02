package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.repository.PlayerRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void deletePlayer(UUID id, UserDetailsImpl user) {
        validateAndRetrievePlayerById(id, user);
        playerRepository.disableUser(id);
    }

    @Override
    public PlayerModel getPlayer(UUID id, UserDetailsImpl user) {
        return validateAndRetrievePlayerById(id, user);
    }

    @Override
    public Page<BasicPlayerDto> playersPaginated(
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            PlayerSortEnum sortField
    ) {
        var pageRequest = PageRequest.of(currentPage, pageSize);

        if (sortDirection != null && sortField != null) {
            pageRequest = pageRequest.withSort(sortDirection, sortField.toString().toLowerCase());
        }

        return playerRepository.listPaginated(pageRequest);
    }

    private PlayerModel validateAndRetrievePlayerById(UUID id, UserDetailsImpl user) {
        if (!(id.equals(user.getId()) || user.isAdmin())) {
            throw new UnauthorizedException();
        }

        Optional<PlayerModel> playerOpt = playerRepository.findById(id);
        if (playerOpt.isEmpty() || !playerOpt.get().isActive()) {
            throw new DataNotFoundException();
        }
        return playerOpt.get();
    }
}
