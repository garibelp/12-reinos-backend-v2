package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.CreateSheetRequest;
import br.com.extratora.twelvekingdoms.dto.request.UpdateSheetCurrentPointsRequest;
import br.com.extratora.twelvekingdoms.dto.response.ErrorResponse;
import br.com.extratora.twelvekingdoms.dto.response.IdResponse;
import br.com.extratora.twelvekingdoms.dto.response.MessageResponse;
import br.com.extratora.twelvekingdoms.dto.response.SheetListResponse;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@Validated
public interface SheetController {
    @Operation(summary = "Create a sheet", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = IdResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<IdResponse> create(@Parameter(hidden = true) UserDetailsImpl user, @Valid CreateSheetRequest request);

    @Operation(summary = "Retrieve sheet details", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SheetModel.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<SheetModel> details(@Parameter(hidden = true) UserDetailsImpl user, UUID id);

    @Operation(summary = "List sheets", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SheetListResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<SheetListResponse> list(
            @Min(0) int currentPage,
            @Min(1) int pageSize,
            Sort.Direction sortDirection,
            SheetSortEnum sortField,
            @Parameter(hidden = true) UserDetailsImpl user
    );

    @Operation(summary = "Delete a sheet", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<MessageResponse> delete(@Parameter(hidden = true) UserDetailsImpl user, UUID id);

    @Operation(summary = "Update current physical, mental and heroism points of a sheet", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<MessageResponse> updateCurrentPoints(
            @Parameter(hidden = true) UserDetailsImpl user,
            UUID id,
            UpdateSheetCurrentPointsRequest request
    );
}
