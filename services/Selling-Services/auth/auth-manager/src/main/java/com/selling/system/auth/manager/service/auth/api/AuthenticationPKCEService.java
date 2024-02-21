package com.selling.system.auth.manager.service.auth.api;

import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.request.pkce.AuthCodeRequest;
import com.selling.system.auth.manager.model.request.pkce.PKCETokenRequest;
import com.selling.system.auth.manager.model.response.pkce.AuthorizationResponse;
import com.selling.system.auth.manager.model.response.pkce.PKCEGrantTypeResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationPKCEService {

    Mono<AuthorizationResponse> generateAuthCode(AuthCodeRequest request);

    Mono<PKCEGrantTypeResponse> authenticate(PKCETokenRequest request);

    Mono<PKCEGrantTypeResponse> refresh(RefreshTokenRequest request);
}
