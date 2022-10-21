package br.com.extratora.twelvekingdoms.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class SignupRequest extends LoginRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 30)
    private String lastName;
    @NotBlank
    @Email
    private String email;
}
