package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.SheetControllerImpl;
import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateDeathRollsRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSort;
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
import static br.com.extratora.twelvekingdoms.enums.Error.INVALID_CREATION_DICES;
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

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).deleteSheet(any(), any());
    }

    @Test
    void givenDeleteCalled_whenInvalidParameter_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/sheets/invalid");

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource({
            ",,,,,",
            ",,,,,false",
            ",,,,,true",
            "1,1,,,",
            "1,1,,,false",
            "1,1,,,true",
            "1,1,ASC,,",
            "1,1,ASC,,false",
            "1,1,ASC,,true",
            "1,1,,username,",
            "1,1,,username,false",
            "1,1,,username,true",
            "1,1,ASC,username,",
            "1,1,ASC,username,false",
            "1,1,ASC,username,true",
            "1,1,ASC,USERNAME,",
            "1,1,ASC,USERNAME,false",
            "1,1,ASC,USERNAME,true",
            "1,1,asc,,",
            "1,1,asc,,false",
            "1,1,asc,,true",
            "1,1,asc,username,",
            "1,1,asc,username,false",
            "1,1,asc,username,true",
            "1,1,asc,USERNAME,",
            "1,1,asc,USERNAME,false",
            "1,1,asc,USERNAME,true",
            "1,1,DESC,,",
            "1,1,DESC,,false",
            "1,1,DESC,,true",
            "1,1,,username,",
            "1,1,,username,false",
            "1,1,,username,true",
            "1,1,DESC,username,",
            "1,1,DESC,username,false",
            "1,1,DESC,username,true",
            "1,1,DESC,USERNAME,",
            "1,1,DESC,USERNAME,false",
            "1,1,DESC,USERNAME,true",
            "1,1,desc,,",
            "1,1,desc,,false",
            "1,1,desc,,true",
            "1,1,desc,username,",
            "1,1,desc,username,false",
            "1,1,desc,username,true",
            "1,1,desc,USERNAME,",
            "1,1,desc,USERNAME,false",
            "1,1,desc,USERNAME,true",
            "1,1,,username,",
            "1,1,,username,false",
            "1,1,,username,true",
            "1,1,ASC,name,",
            "1,1,ASC,name,false",
            "1,1,ASC,name,true",
            "1,1,ASC,NAME,",
            "1,1,ASC,NAME,false",
            "1,1,ASC,NAME,true",
            "1,1,asc,name,",
            "1,1,asc,name,false",
            "1,1,asc,name,true",
            "1,1,asc,NAME,",
            "1,1,asc,NAME,false",
            "1,1,asc,NAME,true",
            "1,1,,name,",
            "1,1,,name,false",
            "1,1,,name,true",
            "1,1,DESC,name,",
            "1,1,DESC,NAME,",
            "1,1,desc,name,",
            "1,1,desc,NAME,",
            "1,1,ASC,level,",
            "1,1,ASC,LEVEL,",
            "1,1,asc,level,",
            "1,1,asc,LEVEL,",
            "1,1,,level,",
            "1,1,DESC,level,",
            "1,1,DESC,LEVEL,",
            "1,1,desc,level,",
            "1,1,desc,LEVEL,",
            "1,1,ASC,active,",
            "1,1,ASC,ACTIVE,",
            "1,1,asc,active,",
            "1,1,asc,ACTIVE,",
            "1,1,,active,",
            "1,1,DESC,active,",
            "1,1,DESC,ACTIVE,",
            "1,1,desc,active,",
            "1,1,desc,ACTIVE,",
    })
    void givenListCalled_whenValidParameters_thenShouldReturnSheetList(
            String currentPage,
            String pageSize,
            String sortDirection,
            String sortField,
            String usePlayerProfile
    ) throws Exception {
        int expectedPageNumber = Optional.ofNullable(currentPage).map(Integer::parseInt).orElse(0);
        int expectedPageSize = Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5);
        var expectedSortDirection = Optional.ofNullable(sortDirection)
                .map(d -> Sort.Direction.valueOf(d.toUpperCase()))
                .orElse(null);
        var expectedSortField = Optional.ofNullable(sortField)
                .map(s -> SheetSort.valueOf(sortField.toUpperCase()))
                .orElse(null);
        var expectedUsePlayerProfile = Optional.ofNullable(usePlayerProfile)
                .map(Boolean::parseBoolean)
                .orElse(false);

        Page<BasicSheetDto> dbData = getBasicSheetDtoPage();
        when(sheetService.sheetsPaginated(
                any(),
                anyInt(),
                anyInt(),
                any(),
                any(),
                anyBoolean(),
                any()
        )).thenReturn(dbData);
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/list")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize)
                .param("sortDirection", sortDirection)
                .param("sortField", sortField)
                .param("usePlayerProfile", usePlayerProfile);

        mockMvc.perform(builder);

        ArgumentCaptor<Integer> pageNumberCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageSizeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Sort.Direction> sortDirectionCaptor = ArgumentCaptor.forClass(Sort.Direction.class);
        ArgumentCaptor<SheetSort> sortFieldCaptor = ArgumentCaptor.forClass(SheetSort.class);
        ArgumentCaptor<Boolean> usePlayerProfileCaptor = ArgumentCaptor.forClass(Boolean.class);

        verify(sheetService, times(1)).sheetsPaginated(
                any(),
                pageNumberCaptor.capture(),
                pageSizeCaptor.capture(),
                sortDirectionCaptor.capture(),
                sortFieldCaptor.capture(),
                usePlayerProfileCaptor.capture(),
                any()
        );

        assertEquals(expectedPageNumber, pageNumberCaptor.getValue());
        assertEquals(expectedPageSize, pageSizeCaptor.getValue());
        assertEquals(expectedSortDirection, sortDirectionCaptor.getValue());
        assertEquals(expectedSortField, sortFieldCaptor.getValue());
        assertEquals(expectedUsePlayerProfile, usePlayerProfileCaptor.getValue());
    }

    @ParameterizedTest
    @CsvSource({
            // Minimum value errors
            "-1,,,,,1",
            ",0,,,,1",
            ",-1,,,,1",
            "-1,-1,,,,2",
            // Enum conversion errors
            ",,inv,,,1",
            ",,,inv,,1",
            ",,inv,inv,,1",
            "-1,,inv,inv,,1",
            "-1,0,inv,inv,,1",
            "-1,-1,inv,inv,,1",
            // Boolean conversion errors
            ",,,,inv,1",
    })
    void givenListCalled_whenInvalidParameters_thenShouldReturnBadRequest(
            String currentPage,
            String pageSize,
            String sortDirection,
            String sortField,
            String usePlayerProfile,
            Integer totalErrors
    ) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/sheets/list")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize)
                .param("sortDirection", sortDirection)
                .param("sortField", sortField)
                .param("usePlayerProfile", usePlayerProfile);

        mockMvc.perform(builder).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse error = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertEquals(totalErrors, error.getErrorList().size());
                });
    }

    @Test
    void givenUpdateCurrentPoints_whenValidRequest_thenShouldUpdateSheetCurrentPoints() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID_1.toString() + "/currentPoints")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateSheetCurrentPointsRequest.builder().build()));

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).updateCurrentPoints(any(), any(), any());
    }

    @Test
    void givenUpdateCurrentPoints_whenRequestWithoutBody_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID_1.toString() + "/currentPoints");

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).updateCurrentPoints(any(), any(), any());
    }

    @Test
    void givenUpdateCurrentPointsCalled_whenInvalidUUIDFormat_thenShouldReturnErrorResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/invalid/currentPoints");

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).updateCurrentPoints(any(), any(), any());
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenLevelUp_whenInvalidUuid_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + id + "/levelUp");

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).levelUp(any(), any());
    }

    @Test
    void givenLevelUp_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID.randomUUID() + "/levelUp");

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).levelUp(any(), any());
    }

    @ParameterizedTest
    @CsvSource({
            "' ',042a1652-d31f-4197-bbc5-1abbfeedd8b1",
            "invalid,042a1652-d31f-4197-bbc5-1abbfeedd8b1",
            "042a1652-d31f-4197-bbc5-1abbfeedd8b1,invalid",
            "042a1652-d31f-4197-bbc5-1abbfeedd8b1,' '"
    })
    void givenAddWound_whenInvalidUuid_thenShouldReturnBadRequest(String sheetId, String woundId) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + sheetId + "/addWound")
                .param("woundId", woundId);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).addWound(any(), any(), any());
    }

    @Test
    void givenAddWound_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID.randomUUID() + "/addWound")
                .param("woundId", UUID.randomUUID().toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).addWound(any(), any(), any());
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenRemoveWound_whenInvalidUuid_thenShouldReturnBadRequest(String sheetId) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + sheetId + "/removeWound");

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).removeWound(any(), any());
    }

    @Test
    void givenRemoveWound_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID.randomUUID() + "/removeWound");

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).removeWound(any(), any());
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("br.com.extratora.twelvekingdoms.TestPayloads#getInvalidDeathRollsRequestStream")
    void givenUpdateDeathRolls_whenInvalidBody_thenShouldReturnBadRequest(UpdateDeathRollsRequest req) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID.randomUUID() + "/updateDeathRolls")
                .content(objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(sheetService, times(0)).updateDeathRolls(any(), any(), any());
    }

    @Test
    void givenUpdateDeathRolls_whenValidRequest_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/sheets/" + UUID.randomUUID() + "/updateDeathRolls")
                .content(objectMapper.writeValueAsString(getValidDeathRollsRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(sheetService, times(1)).updateDeathRolls(any(), any(), any());
    }
}
