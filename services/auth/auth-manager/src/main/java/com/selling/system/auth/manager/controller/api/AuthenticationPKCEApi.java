package com.selling.system.auth.manager.controller.api;

import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.request.pkce.AuthCodeRequest;
import com.selling.system.auth.manager.model.request.pkce.PKCETokenRequest;
import com.selling.system.auth.manager.model.response.pkce.AuthorizationResponse;
import com.selling.system.auth.manager.model.response.pkce.PKCEGrantTypeResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/pkce")
@Tag(name = "PKCE Authentication Controller", description = "Operations related to PKCE authentication grant type")
public interface AuthenticationPKCEApi {

    @PostMapping("/code")
    @ApiResponse(description = "Contains the credentials needed to generate the authorization code.")
    Mono<ResponseEntity<AuthorizationResponse>> code(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contains the secrets to authenticate based on them")
                                                     @RequestBody @Valid AuthCodeRequest request);

    @PostMapping("/auth")
    @ApiResponse(description = "Contains the credentials needed to generate the tokens.")
    Mono<ResponseEntity<PKCEGrantTypeResponse>> authenticate(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contains the secrets to authenticate based on them")
                                                             @RequestBody @Valid PKCETokenRequest request);


    @PostMapping("/refresh")
    Mono<ResponseEntity<PKCEGrantTypeResponse>> refresh(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Contains the refresh token to issue a new access token")
            @RequestBody @Valid RefreshTokenRequest request);
}
