package com.selling.system.auth.manager.controller.impl;

import com.selling.system.auth.manager.controller.api.AuthenticationPKCEApi;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.request.pkce.AuthCodeRequest;
import com.selling.system.auth.manager.model.request.pkce.PKCETokenRequest;
import com.selling.system.auth.manager.model.response.pkce.AuthorizationResponse;
import com.selling.system.auth.manager.model.response.pkce.PKCEGrantTypeResponse;
import com.selling.system.auth.manager.service.auth.api.AuthenticationPKCEService;
import com.selling.system.shared.module.wrapper.WebResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationPKCEController extends WebResponseWrapper implements AuthenticationPKCEApi {

    private final AuthenticationPKCEService authenticationPKCEService;

    @Override
    public Mono<ResponseEntity<AuthorizationResponse>> code(AuthCodeRequest request) {
        return response(authenticationPKCEService.generateAuthCode(request), HttpStatus.CREATED);
    }

    @Override
    public Mono<ResponseEntity<PKCEGrantTypeResponse>> authenticate(PKCETokenRequest request) {
        return response(authenticationPKCEService.authenticate(request), HttpStatus.CREATED);
    }

    @Override
    public Mono<ResponseEntity<PKCEGrantTypeResponse>> refresh(RefreshTokenRequest request) {
        return response(authenticationPKCEService.refresh(request), HttpStatus.CREATED);
    }
}
