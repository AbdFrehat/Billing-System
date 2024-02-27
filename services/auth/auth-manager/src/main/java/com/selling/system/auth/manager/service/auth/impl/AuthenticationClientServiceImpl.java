package com.selling.system.auth.manager.service.auth.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.exception.BadCredentialException;
import com.selling.system.auth.manager.exception.ExpiredRefreshTokenException;
import com.selling.system.auth.manager.exception.InvalidRefreshTokenException;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.auth.manager.model.request.client.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.response.client.ClientGrantTypeResponse;
import com.selling.system.auth.manager.service.auth.api.AuthenticationClientService;
import com.selling.system.auth.manager.service.client.api.ClientsServiceClient;
import com.selling.system.auth.manager.service.token.api.TokenService;
import com.selling.system.auth.manager.util.DateUtil;
import com.selling.system.auth.manager.util.JWTsUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

import static com.selling.system.auth.manager.util.MatcherUtil.isClientMatch;

@Service
public class AuthenticationClientServiceImpl implements AuthenticationClientService {

    private final ClientsServiceClient clientsServiceClient;
    private final TokenService<ClientResponse> accessTokenClientService;
    private final TokenService<ClientResponse> refreshTokenClientService;

    private final LocalAppConfig localAppConfig;

    public AuthenticationClientServiceImpl(ClientsServiceClient clientsServiceClient,
                                           @Qualifier("clientAccessToken") TokenService<ClientResponse> accessTokenClientService,
                                           @Qualifier("clientRefreshToken") TokenService<ClientResponse> refreshTokenClientService,
                                           LocalAppConfig localAppConfig) {
        this.clientsServiceClient = clientsServiceClient;
        this.accessTokenClientService = accessTokenClientService;
        this.refreshTokenClientService = refreshTokenClientService;
        this.localAppConfig = localAppConfig;
    }

    @Override
    public Mono<ClientGrantTypeResponse> authenticate(IssueClientTokenRequest request) {
        return clientsServiceClient.retrieveClientById(request.getClientId())
                .handle(($, sink) -> {
                    if (isClientMatch(request.getClientId(), request.getClientSecret(), $.getClientId(), $.getClientSecret())) {
                        LocalDateTime date = LocalDateTime.now();
                        Date accessExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getAccessToken().getExpirationTime());
                        Date refreshExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getRefreshToken().getExpirationTime());
                        sink.next(ClientGrantTypeResponse.builder()
                                .accessToken(accessTokenClientService.buildToken($, accessExpirationDate))
                                .refreshToken(refreshTokenClientService.buildToken($, refreshExpirationDate))
                                .issuedAt(date)
                                .expiredAt(accessExpirationDate)
                                .build());
                        return;
                    }
                    sink.error(new BadCredentialException("Invalid Credentials"));
                });
    }

    @Override
    public Mono<ClientGrantTypeResponse> refresh(RefreshTokenRequest request) {
        try {
            Claims claims = JWTsUtil.isTokenValid(request.getRefreshToken(), localAppConfig.getAuth().getKey());
            String clientId = (String) claims.get("clientId");
            LocalDateTime date = LocalDateTime.now();
            Date accessExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getAccessToken().getExpirationTime());
            Date refreshExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getRefreshToken().getExpirationTime());
            return clientsServiceClient.retrieveClientById(clientId)
                    .map($ -> ClientGrantTypeResponse.builder()
                            .accessToken(accessTokenClientService.buildToken($, accessExpirationDate))
                            .refreshToken(refreshTokenClientService.buildToken($, refreshExpirationDate))
                            .issuedAt(date)
                            .expiredAt(accessExpirationDate)
                            .build());
        } catch (ExpiredJwtException ex) {
            throw new ExpiredRefreshTokenException();
        } catch (Exception ex) {
            throw new InvalidRefreshTokenException();
        }
    }
}
