package br.com.extratora.twelvekingdoms.exception;

import br.com.extratora.twelvekingdoms.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException {
    ErrorEnum error;
}
