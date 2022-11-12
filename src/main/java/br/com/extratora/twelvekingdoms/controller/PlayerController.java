package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.PlayerListResponse;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.UUID;

@Validated

public interface PlayerController {
    @Operation(summary = "Delete a player", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    ResponseEntity<MessageResponse> delete(UserDetailsImpl user, UUID id);

    @Operation(summary = "Retrieve player details", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerModel.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    ResponseEntity<PlayerModel> details(UserDetailsImpl user, UUID id);

    @Operation(summary = "List players", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerListResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401")
    })
    ResponseEntity<PlayerListResponse> list(
            @Min(0) int currentPage,
            @Min(1) int pageSize,
            Sort.Direction sortDirection,
            PlayerSortEnum sortField
    );
}
