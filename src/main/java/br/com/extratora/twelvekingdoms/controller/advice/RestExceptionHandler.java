package br.com.extratora.twelvekingdoms.controller.advice;

import br.com.extratora.twelvekingdoms.dto.response.ErrorDto;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.exception.ResponseFieldStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse res = new ErrorResponse();
        List<ErrorDto> errorList = res.getErrorList();
        ex.getFieldErrors().forEach(fieldError -> errorList.add(new ErrorDto(fieldError.getField(), fieldError.getDefaultMessage())));
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(ResponseFieldStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseFieldStatusException ex) {
        ErrorResponse res = new ErrorResponse();
        res.getErrorList().add(new ErrorDto(ex.getField(), ex.getReason()));
        return ResponseEntity.badRequest().body(res);
    }
}
