package com.selling.system.auth.clients.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.GrantTypesDto;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeCreateRequest;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeDeleteRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/grant/types")
@Tag(name = "Grant Types Controller", description = "Operations related to grant types.")
public interface GrantTypesApi {

    @GetMapping
    @Operation(summary = "Retrieve grant types list")
    @ApiResponse(description = "Contains the names of the grant types")
    Mono<ResponseEntity<GrantTypesDto>> retrieveGrantTypes();

    @PostMapping
    @Operation(summary = "Create a new grant type")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> createGrantType(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to create a new grant type")
                                                              @RequestBody @Valid GrantTypeCreateRequest request);

    @DeleteMapping
    @Operation(summary = "Delete a grant type")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteGrantType(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to delete the grant type")
                                                              @RequestBody @Valid GrantTypeDeleteRequest request);

}
