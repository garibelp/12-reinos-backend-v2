package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.repository.PlayerRepository;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import br.com.extratora.twelvekingdoms.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PlayerServiceTests {
    private final UserDetailsImpl admin = getUserDetailsAdmin();
    private final UserDetailsImpl user = getUserDetailsUser();
    @Captor
    private ArgumentCaptor<PageRequest> pageRequestCaptor;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void givenDeletePlayer_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        assertThrows(
                UnauthorizedException.class,
                () -> playerService.deletePlayer(PLAYER_2_UUID, user)
        );

        verify(playerRepository, times(0)).findById(PLAYER_2_UUID);
        verify(playerRepository, times(0)).disableUser(PLAYER_2_UUID);
    }

    @Test
    void givenDeletePlayer_whenUserNotFoundOnDb_thenThrowDataNotFoundException() {
        when(playerRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> playerService.deletePlayer(PLAYER_2_UUID, admin)
        );

        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
        verify(playerRepository, times(0)).disableUser(PLAYER_2_UUID);
    }

    @Test
    void givenDeletePlayer_whenUserInactive_thenThrowDataNotFoundException() {
        when(playerRepository.findById(PLAYER_2_UUID))
                .thenReturn(Optional.of(getPlayerModel(PLAYER_2_UUID, "user2", true, false)));

        assertThrows(
                DataNotFoundException.class,
                () -> playerService.deletePlayer(PLAYER_2_UUID, admin)
        );

        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
        verify(playerRepository, times(0)).disableUser(PLAYER_2_UUID);
    }

    @Test
    void givenDeletePlayer_whenValid_thenDisableUser() {
        when(playerRepository.findById(PLAYER_2_UUID))
                .thenReturn(Optional.of(getPlayerModel(PLAYER_2_UUID, "user2", true, true)));
        when(playerRepository.findById(PLAYER_UUID))
                .thenReturn(Optional.of(getPlayerModel(PLAYER_UUID, "user", false, true)));

        playerService.deletePlayer(PLAYER_2_UUID, admin);
        playerService.deletePlayer(PLAYER_UUID, user);

        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
        verify(playerRepository, times(1)).findById(PLAYER_UUID);
        verify(playerRepository, times(2)).save(any());
    }

    @Test
    void givenGetPlayer_whenUserNotAdminAndDifferentUUIDs_thenThrowUnauthorizedException() {
        assertThrows(
                UnauthorizedException.class,
                () -> playerService.getPlayer(PLAYER_2_UUID, user)
        );

        verify(playerRepository, times(0)).findById(PLAYER_2_UUID);
    }

    @Test
    void givenGetPlayer_whenUserNotFoundOnDb_thenThrowDataNotFoundException() {
        when(playerRepository.findById(PLAYER_2_UUID)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> playerService.getPlayer(PLAYER_2_UUID, admin)
        );

        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
    }

    @Test
    void givenGetPlayer_whenUserInactive_thenThrowDataNotFoundException() {
        when(playerRepository.findById(PLAYER_2_UUID))
                .thenReturn(Optional.of(getPlayerModel(PLAYER_2_UUID, "user2", true, false)));

        assertThrows(
                DataNotFoundException.class,
                () -> playerService.getPlayer(PLAYER_2_UUID, admin)
        );

        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
    }

    @Test
    void givenGetPlayer_whenValid_thenDisableUser() {
        PlayerModel expected = getPlayerModel(PLAYER_2_UUID, "user2", true, true);
        when(playerRepository.findById(PLAYER_2_UUID))
                .thenReturn(Optional.of(expected));
        when(playerRepository.findById(PLAYER_UUID))
                .thenReturn(Optional.of(getPlayerModel(PLAYER_UUID, "user", false, true)));

        PlayerModel retrieved = playerService.getPlayer(PLAYER_2_UUID, admin);
        PlayerModel retrievedOwn = playerService.getPlayer(PLAYER_UUID, user);

        assertEquals(expected, retrieved);
        assertEquals(user.getId(), retrievedOwn.getId());
        verify(playerRepository, times(1)).findById(PLAYER_2_UUID);
    }

    @ParameterizedTest
    @CsvSource({
            "0,1,,",
            "0,1,ASC,",
            "0,1,,USERNAME",
            "0,1,ASC,USERNAME",
            "0,1,DESC,USERNAME",
            "0,1,ASC,EMAIL",
            "0,1,DESC,EMAIL",
    })
    void givenPlayersPaginated_whenValid_thenCallWithPagination(
            int currentPage,
            int pageSize,
            Sort.Direction sortDirection,
            PlayerSortEnum sortField
    ) {
        playerService.playersPaginated(currentPage, pageSize, sortDirection, sortField);

        verify(playerRepository, times(1)).listPaginated(pageRequestCaptor.capture());
        Pageable page = pageRequestCaptor.getValue();

        assertEquals(currentPage, page.getPageNumber());
        assertEquals(pageSize, page.getPageSize());
        if (sortDirection == null || sortField == null) {
            assertEquals(Sort.unsorted(), page.getSort());
        } else {
            Sort.Order sort = page.getSort().get().findFirst().get();
            assertEquals(sortDirection, sort.getDirection());
            assertEquals(sortField.name(), sort.getProperty().toUpperCase());
        }
    }
}
