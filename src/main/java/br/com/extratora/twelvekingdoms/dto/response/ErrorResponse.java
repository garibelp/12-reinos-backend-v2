package br.com.extratora.twelvekingdoms.dto.response;

import br.com.extratora.twelvekingdoms.dto.ErrorDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private List<ErrorDto> errorList = new ArrayList<>();
}
