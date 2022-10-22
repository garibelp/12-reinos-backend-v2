package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.LoginRequest;
import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import br.com.extratora.twelvekingdoms.dto.response.JwtResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest);
}
