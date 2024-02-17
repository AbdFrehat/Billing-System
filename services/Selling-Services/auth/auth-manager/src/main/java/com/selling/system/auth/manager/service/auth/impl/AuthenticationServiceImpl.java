package com.selling.system.auth.manager.service.auth.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.exception.BadCredentialException;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.auth.manager.model.request.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.response.ClientGrantTypeResponse;
import com.selling.system.auth.manager.service.auth.api.AuthenticationService;
import com.selling.system.auth.manager.service.client.api.ClientsServiceClient;
import com.selling.system.auth.manager.service.token.api.AccessTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.selling.system.auth.manager.util.MatcherUtil.isClientMatch;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ClientsServiceClient clientsServiceClient;
    private final AccessTokenService<ClientResponse> accessTokenClientService;
    private final AccessTokenService<ClientResponse> refreshTokenClientService;

    private final LocalAppConfig appConfig;

    public AuthenticationServiceImpl(ClientsServiceClient clientsServiceClient,
                                     @Qualifier("clientAccessToken") AccessTokenService<ClientResponse> accessTokenClientService,
                                     @Qualifier("clientRefreshToken") AccessTokenService<ClientResponse> refreshTokenClientService, LocalAppConfig appConfig) {
        this.clientsServiceClient = clientsServiceClient;
        this.accessTokenClientService = accessTokenClientService;
        this.refreshTokenClientService = refreshTokenClientService;
        this.appConfig = appConfig;
    }

    @Override
    public Mono<ClientGrantTypeResponse> authenticate(IssueClientTokenRequest issueClientTokenRequest) {
        return clientsServiceClient.retrieveClientById(issueClientTokenRequest.getClientId())
                .handle(($, sink) -> {
                    if (isClientMatch(issueClientTokenRequest.getClientId(), issueClientTokenRequest.getClientSecret(), $.getClientId(), $.getClientSecret())) {
                        sink.next(ClientGrantTypeResponse.builder()
                                .accessToken(accessTokenClientService.buildToken($))
                                .refreshToken(refreshTokenClientService.buildToken($))
                                .issuedAt(LocalDateTime.now())
                                .expiredAt(LocalDateTime.now().plus(Duration.ofSeconds(appConfig.getAuth().getAccessToken().getExpirationTime())))
                                .build());
                        return;
                    }
                    sink.error(new BadCredentialException("Invalid Credentials"));
                });
    }
}
