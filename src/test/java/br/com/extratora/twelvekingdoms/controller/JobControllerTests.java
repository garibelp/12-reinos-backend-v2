package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.JobControllerImpl;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.service.JobService;
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
import static br.com.extratora.twelvekingdoms.TestPayloads.getJobModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class JobControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JobService jobService;

    @Test
    void givenListCalled_whenUserLogger_thenShouldRetrieveList() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/jobs/list");

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(jobService, times(1)).jobList();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @CsvSource("invalid")
    void givenDetailsCalled_whenInvalidUUID_thenShouldReturnBadRequest(String id) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/jobs")
                .param("id", id);

        mockMvc.perform(builder).andExpect(status().isBadRequest());

        verify(jobService, times(0)).getJob(any());
    }

    @Test
    void givenDetailsCalled_whenUUIDNotFound_thenShouldReturnNotFound() throws Exception {
        when(jobService.getJob(UUID_1)).thenThrow(new DataNotFoundException());
        RequestBuilder builder = MockMvcRequestBuilders.get("/jobs")
                .param("id", UUID_1.toString());

        mockMvc.perform(builder).andExpect(status().isNotFound());

        verify(jobService, times(1)).getJob(UUID_1);
    }

    @Test
    void givenDetailsCalled_whenUUIDFound_thenShouldReturnData() throws Exception {
        when(jobService.getJob(UUID_1)).thenReturn(getJobModel());
        RequestBuilder builder = MockMvcRequestBuilders.get("/jobs")
                .param("id", UUID_1.toString());

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(jobService, times(1)).getJob(UUID_1);
    }

}
