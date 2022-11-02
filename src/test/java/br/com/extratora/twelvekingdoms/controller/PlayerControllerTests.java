package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.PlayerControllerImpl;
import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.UnauthorizedException;
import br.com.extratora.twelvekingdoms.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.PLAYER_UUID;
import static br.com.extratora.twelvekingdoms.TestPayloads.getPlayerDtoPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class PlayerControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;

    @Test
    void givenDeleteCalled_whenValidRequest_thenShouldDeletePlayer() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/players/" + PLAYER_UUID.toString());

        mockMvc.perform(builder).andExpect(status().isAccepted());

        verify(playerService, times(1)).deletePlayer(any(), any());
    }

    @Test
    void givenDeleteCalled_whenInvalidParameter_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/players/invalid");

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    void givenDetailsCalled_whenValidRequest_thenShouldReturnPlayerInfo() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/players/" + PLAYER_UUID.toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(playerService, times(1)).getPlayer(any(), any());
    }

    @Test
    void givenDetailsCalled_whenMissingUserPermission_thenShouldReturnUnauthorized() throws Exception {
        when(playerService.getPlayer(any(), any())).thenThrow(new UnauthorizedException());
        RequestBuilder builder = MockMvcRequestBuilders.get("/players/" + PLAYER_UUID.toString());

        mockMvc.perform(builder).andExpect(status().isUnauthorized());

        verify(playerService, times(1)).getPlayer(any(), any());
    }

    @Test
    void givenDetailsCalled_whenNotFound_thenShouldReturnNotFound() throws Exception {
        when(playerService.getPlayer(any(), any())).thenThrow(new DataNotFoundException(""));
        RequestBuilder builder = MockMvcRequestBuilders.get("/players/" + PLAYER_UUID.toString());

        mockMvc.perform(builder).andExpect(status().isNotFound());

        verify(playerService, times(1)).getPlayer(any(), any());
    }

    @Test
    void givenDetailsCalled_whenInvalidParameter_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/players/invalid");

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource({
            ",,,",
            "1,1,,",
            "1,1,ASC,",
            "1,1,,username",
            "1,1,ASC,username",
            "1,1,ASC,USERNAME",
            "1,1,asc,",
            "1,1,asc,username",
            "1,1,asc,USERNAME",
            "1,1,DESC,",
            "1,1,,username",
            "1,1,DESC,username",
            "1,1,DESC,USERNAME",
            "1,1,desc,",
            "1,1,desc,username",
            "1,1,desc,USERNAME",
            "1,1,,username",
            "1,1,ASC,email",
            "1,1,ASC,EMAIL",
            "1,1,asc,email",
            "1,1,asc,EMAIL",
            "1,1,,email",
            "1,1,DESC,email",
            "1,1,DESC,EMAIL",
            "1,1,desc,email",
            "1,1,desc,EMAIL",
    })
    void givenListCalled_whenValidParameters_thenShouldReturnPlayerList(
            String currentPage,
            String pageSize,
            String sortDirection,
            String sortField
    ) throws Exception {
        int expectedPageNumber = Optional.ofNullable(currentPage).map(Integer::parseInt).orElse(0);
        int expectedPageSize = Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5);
        var expectedSortDirection = Optional.ofNullable(sortDirection)
                .map(d -> Sort.Direction.valueOf(d.toUpperCase()))
                .orElse(null);
        var expectedSortField = Optional.ofNullable(sortField)
                .map(s -> PlayerSortEnum.valueOf(sortField.toUpperCase()))
                .orElse(null);

        Page<BasicPlayerDto> dbData = getPlayerDtoPage();
        when(playerService.playersPaginated(expectedPageNumber, expectedPageSize, expectedSortDirection, expectedSortField)).thenReturn(dbData);
        RequestBuilder builder = MockMvcRequestBuilders.get("/players")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize)
                .param("sortDirection", sortDirection)
                .param("sortField", sortField);

        mockMvc.perform(builder);

        verify(playerService, times(1)).playersPaginated(
                expectedPageNumber,
                expectedPageSize,
                expectedSortDirection,
                expectedSortField
        );
    }

    @ParameterizedTest
    @CsvSource({
            // Minimum value errors
            "-1,,,,1",
            ",0,,,1",
            ",-1,,,1",
            "-1,-1,,,2",
            // Enum conversion errors
            ",,inv,,1",
            ",,,inv,1",
            ",,inv,inv,1",
            "-1,,inv,inv,1",
            "-1,0,inv,inv,1",
            "-1,-1,inv,inv,1",
    })
    void givenListCalled_whenInvalidParameters_thenShouldReturnUnauthorized(
            String currentPage,
            String pageSize,
            String sortDirection,
            String sortField,
            Integer totalErrors
    ) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/players")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize)
                .param("sortDirection", sortDirection)
                .param("sortField", sortField);

        mockMvc.perform(builder).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse error = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertEquals(totalErrors, error.getErrorList().size());
                });
    }
}
