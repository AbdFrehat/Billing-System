package com.selling.system.auth.clients.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.ClientDto;
import com.selling.system.auth.shared.module.models.dto.ClientsDto;
import com.selling.system.auth.shared.module.models.request.client.ClientDeleteRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientInsertRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateGrantTypeRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateProfileRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Client Controller", description = "Operations related to clients.")
public interface ClientsApi {

    @GetMapping
    @Operation(summary = "Retrieve all clients details")
    @ApiResponse(description = "Contains the details of the clients")
    Mono<ResponseEntity<ClientsDto>> retrieveAllClients();

    @GetMapping("/{clientId}")
    @Operation(summary = "Retrieve the client details")
    @ApiResponse(description = "Contains the details of the client")
    Mono<ResponseEntity<ClientDto>> retrieveClientById(
            @Parameter(required = true, description = "Specifies the identifier of the client")
            @PathVariable("clientId") String clientId);

    @PatchMapping("/profile")
    @Operation(summary = "Update the client profile")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateClientProfile(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to update the client profile")
                                                                  @RequestBody @Valid ClientUpdateProfileRequest request);

    @PatchMapping("/grant")
    @Operation(summary = "Update the client grant type")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateClientGrantType(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to update the client grant type")
                                                                    @RequestBody @Valid ClientUpdateGrantTypeRequest request);

    @DeleteMapping
    @Operation(summary = "delete the client specified by the request")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteClient(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to delete the client")
                                                           @RequestBody @Valid ClientDeleteRequest request);

    @PostMapping
    @Operation(summary = "Create a new client")
    @ApiResponse(description = "Contains the number of the updated records, if the value is 0, nothing is happened")
    Mono<ResponseEntity<UpdatedRowsResponse>> createClient(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Specifies the fields needed to create a new client")
                                                           @RequestBody @Valid ClientInsertRequest request);

    @GetMapping("/exists/{clientName}")
    @Operation(summary = "Check if a specific client name already exists")
    @ApiResponse(description = "Contains if the given client name exists")
    Mono<ResponseEntity<NameExistenceResponse>> isClientExist(@Parameter(required = true,
            description = "Specifies the client name to check for existence")
                                                              @PathVariable("clientName") String clientName);
}
