package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.LineageControllerImpl;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.service.LineageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.extratora.twelvekingdoms.TestPayloads.UUID_1;
import static br.com.extratora.twelvekingdoms.TestPayloads.getLineageModel;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LineageControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class LineageControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LineageService lineageService;

    @Test
    void givenListCalled_whenUserLogger_thenShouldRetrieveList() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/lineages/list");

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(lineageService, times(1)).lineageList();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @CsvSource("invalid")
    void givenDetailsCalled_whenInvalidUUID_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/lineages")
                .param("id", id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(lineageService, times(0)).getLineage(any());
    }

    @Test
    void givenDetailsCalled_whenUUIDNotFound_thenShouldReturnNotFound() throws Exception {
        when(lineageService.getLineage(UUID_1)).thenThrow(new DataNotFoundException());
        RequestBuilder builder = MockMvcRequestBuilders.get("/lineages")
                .param("id", UUID_1.toString());

        mockMvc.perform(builder).andExpect(status().isNotFound());

        verify(lineageService, times(1)).getLineage(UUID_1);
    }

    @Test
    void givenDetailsCalled_whenUUIDFound_thenShouldReturnData() throws Exception {
        when(lineageService.getLineage(UUID_1)).thenReturn(getLineageModel());
        RequestBuilder builder = MockMvcRequestBuilders.get("/lineages")
                .param("id", UUID_1.toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(lineageService, times(1)).getLineage(UUID_1);
    }
}
