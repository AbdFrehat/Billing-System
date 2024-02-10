package com.selling.system.auth.manager.controller.impl;

import com.selling.system.auth.manager.controller.api.AuthenticationApi;
import com.selling.system.auth.manager.model.request.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.response.ClientGrantTypeResponse;
import com.selling.system.auth.manager.service.api.AuthenticationService;
import com.selling.system.shared.module.wrapper.WebResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController extends WebResponseWrapper implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    @Override
    public Mono<ResponseEntity<ClientGrantTypeResponse>> authenticate(IssueClientTokenRequest request) {
        return response(authenticationService.authenticate(request), HttpStatus.OK);
    }
}
