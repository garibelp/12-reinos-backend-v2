package br.com.extratora.twelvekingdoms.controller.advice;

import br.com.extratora.twelvekingdoms.dto.ErrorDto;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.exception.ForbiddenException;
import br.com.extratora.twelvekingdoms.exception.InvalidDataException;
import br.com.extratora.twelvekingdoms.exception.ResponseFieldStatusException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var res = new ErrorResponse();

        List<ErrorDto> errorList = res.getErrorList();
        ex.getFieldErrors().forEach(fieldError -> errorList.add(new ErrorDto(fieldError.getField(), fieldError.getDefaultMessage())));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(ResponseFieldStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseFieldStatusException ex) {
        var res = new ErrorResponse();

        res.getErrorList().add(new ErrorDto(ex.getField(), ex.getReason()));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var res = new ErrorResponse();

        String message = "Field {" + ex.getName() + "} expected type: " + Objects.requireNonNull(ex.getRequiredType()).getTypeName();
        res.getErrorList().add(new ErrorDto(ex.getName(), message));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        var res = new ErrorResponse();

        ex.getConstraintViolations().forEach(c -> res.getErrorList().add(new ErrorDto(c.getPropertyPath().toString(), c.getMessage())));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
        var res = new ErrorResponse();

        ex.getFieldErrors().forEach(fieldError -> res.getErrorList().add(new ErrorDto(fieldError.getField(), fieldError.getDefaultMessage())));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        var res = new ErrorResponse();

        var cause = ex.getCause();
        if (cause != null) {
            var exceptionStr = cause.getLocalizedMessage();
            res.getErrorList().add(new ErrorDto(
                    exceptionStr.substring(exceptionStr.lastIndexOf("[") + 1, exceptionStr.lastIndexOf("]")).replace("\"", ""),
                    exceptionStr.substring(0, exceptionStr.indexOf("\n"))
            ));
        } else {
            res.getErrorList().add(new ErrorDto("body", ex.getMessage()));
        }

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Void> handleUnauthorizedException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Void> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        var res = new ErrorResponse();

        res.getErrorList().add(new ErrorDto(ex.getName(), ex.getDescription()));

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(MissingPathVariableException ex) {
        var res = new ErrorResponse();

        res.getErrorList().add(new ErrorDto(ex.getVariableName(), ex.getLocalizedMessage()));

        return ResponseEntity.badRequest().body(res);
    }
}

