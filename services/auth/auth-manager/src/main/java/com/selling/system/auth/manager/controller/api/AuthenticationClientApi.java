package com.selling.system.auth.manager.controller.api;

import com.selling.system.auth.manager.model.request.client.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.response.client.ClientGrantTypeResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/client")
@Tag(name = "Client Authentication Controller", description = "Operations related to client authentication grant type")
public interface AuthenticationClientApi {

    @PostMapping
    @ApiResponse(description = "Contains the credentials needed to authenticate.")
    Mono<ResponseEntity<ClientGrantTypeResponse>> authenticate(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contains the secrets to authenticate based on them")
                                                               @RequestBody @Valid IssueClientTokenRequest request);


    @PostMapping("/refresh")
    Mono<ResponseEntity<ClientGrantTypeResponse>> refresh(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Contains the refresh token to issue a new access token")
            @RequestBody @Valid RefreshTokenRequest request);
}
