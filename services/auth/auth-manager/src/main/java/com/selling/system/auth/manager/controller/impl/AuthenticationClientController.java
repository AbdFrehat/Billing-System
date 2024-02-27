package com.selling.system.auth.manager.controller.impl;

import com.selling.system.auth.manager.controller.api.AuthenticationClientApi;
import com.selling.system.auth.manager.model.request.client.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.response.client.ClientGrantTypeResponse;
import com.selling.system.auth.manager.service.auth.api.AuthenticationClientService;
import com.selling.system.shared.module.wrapper.WebResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationClientController extends WebResponseWrapper implements AuthenticationClientApi {

    private final AuthenticationClientService authenticationService;

    @Override
    public Mono<ResponseEntity<ClientGrantTypeResponse>> authenticate(IssueClientTokenRequest request) {
        return response(authenticationService.authenticate(request), HttpStatus.CREATED);
    }

    @Override
    public Mono<ResponseEntity<ClientGrantTypeResponse>> refresh(RefreshTokenRequest request) {
        return response(authenticationService.refresh(request), HttpStatus.CREATED);
    }
}
