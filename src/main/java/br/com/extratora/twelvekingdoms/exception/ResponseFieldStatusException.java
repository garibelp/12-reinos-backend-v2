package br.com.extratora.twelvekingdoms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ResponseFieldStatusException extends ResponseStatusException {
    private String field;

    public ResponseFieldStatusException(HttpStatus status, String reason, String field) {
        super(status, reason);
        this.field = field;
    }
}
