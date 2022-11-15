package br.com.extratora.twelvekingdoms.exception;

import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException {
    private final String name;
    private final String description;

    public InvalidDataException(ErrorEnum error) {
        this.name = error.getName();
        this.description = error.getDescription();
    }
}
