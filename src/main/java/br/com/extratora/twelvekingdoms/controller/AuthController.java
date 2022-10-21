package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.LoginRequest;
import br.com.extratora.twelvekingdoms.dto.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
