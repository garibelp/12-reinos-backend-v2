package br.com.extratora.twelvekingdoms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataNotFoundException extends RuntimeException {
    private final String field;
}
