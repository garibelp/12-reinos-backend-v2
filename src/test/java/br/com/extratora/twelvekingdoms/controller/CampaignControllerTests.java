package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.CampaignControllerImpl;
import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.dto.response.IdResponse;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import br.com.extratora.twelvekingdoms.service.CampaignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.extratora.twelvekingdoms.TestPayloads.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampaignControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class CampaignControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CampaignService campaignService;

    @ParameterizedTest
    @NullSource
    @EmptySource
    @CsvSource({
            "1234",
            "A123456789012345678901234567890"
    })
    void givenCampaignCreation_whenInvalidBody_thenShouldReturnBadRequest(String name) throws Exception {
        var body = CreateCampaignRequest.builder()
                .name(name)
                .build();

        RequestBuilder builder = MockMvcRequestBuilders.post("/campaigns")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    void givenCampaignCreation_whenValidBody_thenShouldReturnSuccess() throws Exception {
        var body = CreateCampaignRequest.builder()
                .name("VALID")
                .build();
        when(campaignService.createCampaign(any(), any())).thenReturn(CampaignModel.builder().id(UUID_1).build());

        RequestBuilder builder = MockMvcRequestBuilders.post("/campaigns")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(result -> {
                    IdResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);
                    assertTrue(response.toString().contains(UUID_1.toString()));
                });
    }

    @Test
    void givenAddSheets_whenInvalidUuidPath_thenShouldReturnBadRequest() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/addSheets/invalid")
                .content(objectMapper.writeValueAsString(getValidIdListRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).addSheetsToCampaign(any(), any(), any());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void givenAddSheets_whenInvalidBody_thenShouldReturnBadRequest(List<UUID> list) throws Exception {
        var body = getValidIdListRequest();
        body.setIdList(list);
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/addSheets/" + UUID_1)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).addSheetsToCampaign(any(), any(), any());
    }

    @Test
    void givenAddSheets_whenValidUuidPath_thenShouldReturnSuccess() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/addSheets/" + UUID_1)
                .content(objectMapper.writeValueAsString(getValidIdListRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(campaignService, times(1)).addSheetsToCampaign(any(), any(), any());
    }

    @Test
    void givenRemoveSheets_whenInvalidUuidPath_thenShouldReturnBadRequest() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/removeSheets/invalid")
                .content(objectMapper.writeValueAsString(getValidIdListRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).removeSheetsFromCampaign(any(), any(), any());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void givenRemoveSheets_whenInvalidBody_thenShouldReturnBadRequest(List<UUID> list) throws Exception {
        var body = getValidIdListRequest();
        body.setIdList(list);
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/removeSheets/" + UUID_1)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).removeSheetsFromCampaign(any(), any(), any());
    }

    @Test
    void givenRemoveSheets_whenValidUuidPath_thenShouldReturnSuccess() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.patch("/campaigns/removeSheets/" + UUID_1)
                .content(objectMapper.writeValueAsString(getValidIdListRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(campaignService, times(1)).removeSheetsFromCampaign(any(), any(), any());
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenDeleteCampaign_whenInvalidUuid_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/campaigns/" + id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).deleteCampaign(any(), any());
    }

    @Test
    void givenDeleteCampaign_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.delete("/campaigns/" + UUID.randomUUID());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(campaignService, times(1)).deleteCampaign(any(), any());
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "1,1",
            ",1",
            "1,"
    })
    void givenListCalled_whenValidParameters_thenShouldReturnSheetList(String currentPage, String pageSize) throws Exception {
        int expectedPageNumber = Optional.ofNullable(currentPage).map(Integer::parseInt).orElse(0);
        int expectedPageSize = Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5);

        Page<BasicCampaignDto> dbData = getBasicCampaignDtoPage();
        when(campaignService.campaignsPaginated(any(), anyInt(), anyInt())).thenReturn(dbData);

        RequestBuilder builder = MockMvcRequestBuilders.get("/campaigns/list")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize);

        mockMvc.perform(builder);

        ArgumentCaptor<Integer> pageNumberCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageSizeCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(campaignService, times(1)).campaignsPaginated(
                any(),
                pageNumberCaptor.capture(),
                pageSizeCaptor.capture()
        );

        assertEquals(expectedPageNumber, pageNumberCaptor.getValue());
        assertEquals(expectedPageSize, pageSizeCaptor.getValue());
    }

    @ParameterizedTest
    @CsvSource({
            "-1,,1",
            ",0,1",
            ",-1,1",
            "-1,-1,2"
    })
    void givenListCalled_whenInvalidParameters_thenShouldReturnBadRequest(
            String currentPage,
            String pageSize,
            Integer totalErrors
    ) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/campaigns/list")
                .param("currentPage", currentPage)
                .param("pageSize", pageSize);

        mockMvc.perform(builder).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse error = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertEquals(totalErrors, error.getErrorList().size());
                });

        verify(campaignService, times(0)).campaignsPaginated(
                any(),
                anyInt(),
                anyInt()
        );
    }

    @ParameterizedTest
    @NullSource
    @CsvSource({
            "' '",
            "invalid"
    })
    void givenGetCampaign_whenInvalidUuid_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/campaigns").param("id", id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(campaignService, times(0)).campaignDetails(any());
    }

    @Test
    void givenGetCampaign_whenValidUuid_thenShouldReturnOk() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/campaigns").param("id", UUID.randomUUID().toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(campaignService, times(1)).campaignDetails(any());
    }
}
