package com.selling.system.auth.manager.service.auth.api;

import com.selling.system.auth.manager.model.request.client.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.response.client.ClientGrantTypeResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationClientService {

    Mono<ClientGrantTypeResponse> authenticate(IssueClientTokenRequest request);

    Mono<ClientGrantTypeResponse> refresh(RefreshTokenRequest request);
}
