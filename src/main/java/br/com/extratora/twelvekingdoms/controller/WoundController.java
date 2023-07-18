package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.response.WoundListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

public interface WoundController {
    @Operation(summary = "List existing wounds", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = WoundListResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<WoundListResponse> list();
}
