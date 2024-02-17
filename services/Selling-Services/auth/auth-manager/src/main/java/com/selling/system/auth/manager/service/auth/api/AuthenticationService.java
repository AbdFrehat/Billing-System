package com.selling.system.auth.manager.service.auth.api;

import com.selling.system.auth.manager.model.request.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.response.ClientGrantTypeResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationService {

    Mono<ClientGrantTypeResponse> authenticate(IssueClientTokenRequest issueClientTokenRequest);
}
