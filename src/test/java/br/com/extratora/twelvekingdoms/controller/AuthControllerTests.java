package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.aggregator.SignupRequestAggregator;
import br.com.extratora.twelvekingdoms.controller.impl.AuthControllerImpl;
import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.repository.PlayerRepository;
import br.com.extratora.twelvekingdoms.repository.RoleRepository;
import br.com.extratora.twelvekingdoms.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.extratora.twelvekingdoms.TestPayloads.getRoleSet;
import static br.com.extratora.twelvekingdoms.TestPayloads.getSignupRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PlayerRepository playerRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private PasswordEncoder encoder;
    @MockBean
    private JwtUtils jwtUtils;

    @ParameterizedTest
    @CsvSource({
            "5,test,,,,",
            "6,'',,,,",
            "4,tests,,,,",
            "4,,Test@001,,,",
            "4,,,,,email@valid",
            "3,tests,Test@001,,,",
            "2,tests,Test@001,Some,,",
            "1,tests,Test@001,Some,User,",
            "1,tests,Test@001,Some,User,invalid",
            "1,tests,Test@001,Some,,email@valid",
            "1,'     ',Test@001,Some,User,email@valid",
            "4,,Test@001,,,",
            "3,,Test@001,Some,,",
            "2,,Test@001,Some,User,",
            "2,tests,Test@001,'    ','    ',email@valid",
    })
    void givenRegisterUserCalled_whenInvalidBodyFields_thenReturnErrorResponse(
            int errorListSize,
            @AggregateWith(SignupRequestAggregator.class) SignupRequest body
    ) throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.post("/auth/signup")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals(
                        errorListSize,
                        objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class)
                                .getErrorList()
                                .size()
                ));
    }

    @ParameterizedTest
    @CsvSource({
            "true,true,1,true,0,0,0,Username already exists!",
            "true,false,1,true,1,0,0,Email is already in use!",
            "false,false,1,false,1,1,1,User registered successfully!",
    })
    void givenRegisterUserCalled_whenValidBodyFields_thenValidateAndReturnResponse(
            boolean expectsError,
            boolean existsByUsernameResponse,
            int existsByUsernameCalledTimes,
            boolean existsByEmailResponse,
            int existsByEmailCalledTimes,
            int findByNameCalledTimes,
            int saveUserCalledTimes,
            String expectedResponseDescription
    ) throws Exception {
        when(playerRepository.existsByUsername(any())).thenReturn(existsByUsernameResponse);
        when(playerRepository.existsByEmail(any())).thenReturn(existsByEmailResponse);
        when(roleRepository.findByName(any())).thenReturn(getRoleSet(false, false).stream().findFirst());

        RequestBuilder builder = MockMvcRequestBuilders.post("/auth/signup")
                .content(objectMapper.writeValueAsString(getSignupRequest()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(expectsError ? status().isBadRequest() : status().isOk())
                .andExpect(result -> {
                    if (expectsError) {
                        assertEquals(
                                expectedResponseDescription,
                                objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class)
                                        .getErrorList()
                                        .get(0)
                                        .getDescription()
                        );
                    } else {
                        assertEquals(
                                expectedResponseDescription,
                                objectMapper.readValue(result.getResponse().getContentAsString(), MessageResponse.class)
                                        .getMessage()
                        );
                    }
                });

        verify(playerRepository, times(existsByUsernameCalledTimes)).existsByUsername(any());
        verify(playerRepository, times(existsByEmailCalledTimes)).existsByEmail(any());
        verify(roleRepository, times(findByNameCalledTimes)).findByName(any());
        verify(playerRepository, times(saveUserCalledTimes)).save(any());
    }
}
