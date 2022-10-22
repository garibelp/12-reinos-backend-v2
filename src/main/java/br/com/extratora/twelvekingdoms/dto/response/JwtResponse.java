package br.com.extratora.twelvekingdoms.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class JwtResponse {
    private String type = "Bearer";
    private String jwt;
    private UUID id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt, UUID id, String username, String email, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
