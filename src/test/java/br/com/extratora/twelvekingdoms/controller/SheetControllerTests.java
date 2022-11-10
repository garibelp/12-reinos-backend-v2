package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.SheetControllerImpl;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.service.SheetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static br.com.extratora.twelvekingdoms.TestPayloads.getValidCreateSheetRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SheetControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class SheetControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SheetService sheetService;

    @ParameterizedTest
    @NullSource
    @MethodSource("br.com.extratora.twelvekingdoms.TestPayloads#getInvalidCreateSheetRequestStream")
    void givenSheetCreation_whenInvalidBody_thenShouldReturnBadRequest(CreateSheetRequest request) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.post("/sheets")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    void givenSheetCreation_whenValidBody_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.post("/sheets")
                .content(objectMapper.writeValueAsString(getValidCreateSheetRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).createSheet(any(), any());
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenGetSheet_whenInvalidUuid_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/" + id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    void givenGetSheet_whenValidUuid_thenShouldReturnOk() throws Exception {
        UUID id = UUID.randomUUID();
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/" + id);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).getSheet(any(), any());
    }
}
