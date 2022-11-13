package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.SheetControllerImpl;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.service.SheetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.UUID;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static br.com.extratora.twelvekingdoms.enums.ErrorEnum.INVALID_CREATION_DICES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    void givenSheetCreation_whenValidBodyValidation_thenShouldReturnOk() throws Exception {
        when(sheetService.createSheet(any(), any())).thenReturn(getSheetModel(UUID_1));
        RequestBuilder builder = MockMvcRequestBuilders.post("/sheets")
                .content(objectMapper.writeValueAsString(getValidCreateSheetRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).createSheet(any(), any());
    }

    @Test
    void givenSheetCreation_whenValidBodyValidationButInvalidDices_thenShouldReturnBadRequest() throws Exception {
        when(sheetService.createSheet(any(), any())).thenThrow(new InvalidDataException(INVALID_CREATION_DICES));
        RequestBuilder builder = MockMvcRequestBuilders.post("/sheets")
                .content(objectMapper.writeValueAsString(getValidCreateSheetRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(1)).createSheet(any(), any());
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenGetSheet_whenInvalidUuid_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets").param("id", id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    void givenGetSheet_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets").param("id", UUID.randomUUID().toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).getSheet(any(), any());
    }

    @Test
    void givenDeleteCalled_whenValidRequest_thenShouldDeleteSheet() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/sheets/" + UUID_1.toString());

        mockMvc.perform(builder).andExpect(status().isAccepted());

        verify(sheetService, times(1)).deleteSheet(any(), any());
    }

    @Test
    void givenDeleteCalled_whenInvalidParameter_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/sheets/invalid");

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
            "1,1,ASC,name",
            "1,1,ASC,NAME",
            "1,1,asc,name",
            "1,1,asc,NAME",
            "1,1,,name",
            "1,1,DESC,name",
            "1,1,DESC,NAME",
            "1,1,desc,name",
            "1,1,desc,NAME",
            "1,1,ASC,level",
            "1,1,ASC,LEVEL",
            "1,1,asc,level",
            "1,1,asc,LEVEL",
            "1,1,,level",
            "1,1,DESC,level",
            "1,1,DESC,LEVEL",
            "1,1,desc,level",
            "1,1,desc,LEVEL",
            "1,1,ASC,active",
            "1,1,ASC,ACTIVE",
            "1,1,asc,active",
            "1,1,asc,ACTIVE",
            "1,1,,active",
            "1,1,DESC,active",
            "1,1,DESC,ACTIVE",
            "1,1,desc,active",
            "1,1,desc,ACTIVE",
    })
    void givenListCalled_whenValidParameters_thenShouldReturnSheetList(
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
                .map(s -> SheetSortEnum.valueOf(sortField.toUpperCase()))
                .orElse(null);

        Page<BasicSheetDto> dbData = getBasicSheetDtoPage();
        when(sheetService.sheetsPaginated(
                any(),
                anyInt(),
                anyInt(),
                any(),
                any()
        )).thenReturn(dbData);
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/list")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize)
                .param("sortDirection", sortDirection)
                .param("sortField", sortField);

        mockMvc.perform(builder);

        ArgumentCaptor<Integer> pageNumberCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageSizeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Sort.Direction> sortDirectionCaptor = ArgumentCaptor.forClass(Sort.Direction.class);
        ArgumentCaptor<SheetSortEnum> sortFieldCaptor = ArgumentCaptor.forClass(SheetSortEnum.class);

        verify(sheetService, times(1)).sheetsPaginated(
                any(),
                pageNumberCaptor.capture(),
                pageSizeCaptor.capture(),
                sortDirectionCaptor.capture(),
                sortFieldCaptor.capture()
        );

        assertEquals(expectedPageNumber, pageNumberCaptor.getValue());
        assertEquals(expectedPageSize, pageSizeCaptor.getValue());
        assertEquals(expectedSortDirection, sortDirectionCaptor.getValue());
        assertEquals(expectedSortField, sortFieldCaptor.getValue());
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
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/list")
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
