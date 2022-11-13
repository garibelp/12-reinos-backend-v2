package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.dto.response.LineageListResponse;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface LineageController {
    @Operation(summary = "List lineages", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LineageListResponse.class))
            ),
            @ApiResponse(responseCode = "401")
    })
    ResponseEntity<LineageListResponse> list();

    @Operation(summary = "Retrieve player details", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LineageModel.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401")
    })
    ResponseEntity<LineageModel> details(UUID id);
}
