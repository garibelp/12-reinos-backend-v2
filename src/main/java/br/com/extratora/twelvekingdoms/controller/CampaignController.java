package br.com.extratora.twelvekingdoms.controller;

import br.com.extratora.twelvekingdoms.dto.request.CreateCampaignRequest;
import br.com.extratora.twelvekingdoms.dto.request.IdListRequest;
import br.com.extratora.twelvekingdoms.dto.response.*;
import br.com.extratora.twelvekingdoms.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@Validated
public interface CampaignController {
    @Operation(summary = "Create a campaign", security = @SecurityRequirement(name = "bearerAuth"))
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
    ResponseEntity<IdResponse> create(
            @Parameter(hidden = true) UserDetailsImpl user,
            @Valid CreateCampaignRequest request
    );

    @Operation(summary = "Add sheets to an existing campaign", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<MessageResponse> addSheets(
            @Parameter(hidden = true) UserDetailsImpl user,
            UUID campaignId,
            @Valid IdListRequest request
    );

    @Operation(summary = "Remove sheets from an existing campaign", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<MessageResponse> removeSheets(
            @Parameter(hidden = true) UserDetailsImpl user,
            UUID campaignId,
            @Valid IdListRequest request
    );

    @Operation(summary = "Delete a campaign", security = @SecurityRequirement(name = "bearerAuth"))
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


    @Operation(summary = "List campaigns", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CampaignListResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<CampaignListResponse> list(
            @Min(0) int currentPage,
            @Min(1) int pageSize,
            @Parameter(hidden = true) UserDetailsImpl user
    );

    @Operation(summary = "Retrieve sheet details", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CampaignDetailsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<CampaignDetailsResponse> details(@Parameter(hidden = true) UserDetailsImpl user, UUID id);
}
