package com.selling.system.auth.manager.service.impl;

import com.selling.system.auth.manager.client.api.ClientsServiceClient;
import com.selling.system.auth.manager.exception.BadCredentialException;
import com.selling.system.auth.manager.model.request.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.response.ClientGrantTypeResponse;
import com.selling.system.auth.manager.service.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.manager.util.MatcherUtil.isClientMatch;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ClientsServiceClient clientsServiceClient;

    @Override
    public Mono<ClientGrantTypeResponse> authenticate(IssueClientTokenRequest issueClientTokenRequest) {
        return clientsServiceClient.retrieveClientById(issueClientTokenRequest.getClientId())
                .handle(($, sink) -> {
                    if (isClientMatch(issueClientTokenRequest.getClientId(), issueClientTokenRequest.getClientSecret(), $.getClientId(), $.getClientSecret())) {
                        sink.next(ClientGrantTypeResponse.builder().build());
                    }
                    sink.error(new BadCredentialException("Invalid Credentials"));
                });
    }
}
