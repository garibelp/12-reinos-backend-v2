package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.controller.impl.WoundControllerImpl;
import br.com.extratora.twelvekingdoms.service.WoundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WoundControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class WoundControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WoundService woundService;

    @Test
    void givenListCalled_whenUserLogger_thenShouldRetrieveList() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/wounds/list");

        mockMvc.perform(builder).andExpect(status().isOk());

        verify(woundService, times(1)).list();
    }
}
