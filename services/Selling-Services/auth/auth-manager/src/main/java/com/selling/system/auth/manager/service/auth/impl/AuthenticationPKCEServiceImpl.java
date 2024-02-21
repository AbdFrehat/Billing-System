package com.selling.system.auth.manager.service.auth.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.exception.*;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.auth.manager.model.client.response.UserResponse;
import com.selling.system.auth.manager.model.entities.AuthDetails;
import com.selling.system.auth.manager.model.request.common.RefreshTokenRequest;
import com.selling.system.auth.manager.model.request.pkce.AuthCodeRequest;
import com.selling.system.auth.manager.model.request.pkce.PKCETokenRequest;
import com.selling.system.auth.manager.model.response.pkce.AuthorizationResponse;
import com.selling.system.auth.manager.model.response.pkce.PKCEGrantTypeResponse;
import com.selling.system.auth.manager.service.auth.api.AuthenticationPKCEService;
import com.selling.system.auth.manager.service.client.api.ClientsServiceClient;
import com.selling.system.auth.manager.service.client.api.UsersServiceClient;
import com.selling.system.auth.manager.service.token.api.TokenService;
import com.selling.system.auth.manager.util.CodeUtil;
import com.selling.system.auth.manager.util.DateUtil;
import com.selling.system.auth.manager.util.JWTsUtil;
import com.selling.system.shared.module.exceptions.general.TechnicalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import static com.selling.system.auth.manager.util.RedisUtil.buildKeyName;

@Service
public class AuthenticationPKCEServiceImpl implements AuthenticationPKCEService {

    private final ClientsServiceClient clientsServiceClient;

    private final UsersServiceClient usersServiceClient;

    private final PasswordEncoder passwordEncoder;

    private final LocalAppConfig localAppConfig;

    private final ReactiveRedisOperations<String, AuthDetails> redisOperations;

    private final Environment environment;

    private final TokenService<AuthDetails> accessTokenPKCEService;
    private final TokenService<AuthDetails> identityTokenPKCEService;
    private final TokenService<AuthDetails> refreshTokenPKCEService;

    public AuthenticationPKCEServiceImpl(ClientsServiceClient clientsServiceClient,
                                         UsersServiceClient usersServiceClient,
                                         PasswordEncoder passwordEncoder,
                                         LocalAppConfig localAppConfig,
                                         ReactiveRedisOperations<String, AuthDetails> redisOperations,
                                         Environment environment,
                                         @Qualifier("pkceAccessToken") TokenService<AuthDetails> accessTokenPKCEService,
                                         @Qualifier("pkceIdentityToken") TokenService<AuthDetails> identityTokenPKCEService,
                                         @Qualifier("pkceRefreshToken") TokenService<AuthDetails> refreshTokenPKCEService) {
        this.clientsServiceClient = clientsServiceClient;
        this.usersServiceClient = usersServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.localAppConfig = localAppConfig;
        this.redisOperations = redisOperations;
        this.environment = environment;
        this.accessTokenPKCEService = accessTokenPKCEService;
        this.identityTokenPKCEService = identityTokenPKCEService;
        this.refreshTokenPKCEService = refreshTokenPKCEService;
    }


    @Override
    public Mono<AuthorizationResponse> generateAuthCode(AuthCodeRequest request) {
        return Mono.zip(clientsServiceClient.retrieveClientById(request.getClientId()),
                        usersServiceClient.retrieveUserByUsername(request.getUsername()))
                .flatMap($ -> {
                    ClientResponse clientResponse = $.getT1();
                    UserResponse userResponse = $.getT2();
                    if (passwordEncoder.matches(request.getPassword(), userResponse.getPassword())) {
                        return validUser(userResponse).flatMap($$ -> {
                            String authCode = CodeUtil.generateAuthCode(localAppConfig.getAuth().getAuthCode().getLength());
                            AuthDetails authDetails = AuthDetails.builder().authCode(authCode)
                                    .clientId(clientResponse.getClientId())
                                    .codeChallenge(request.getCodeChallenge())
                                    .user(userResponse)
                                    .build();
                            return Mono.zip(redisOperations.opsForValue()
                                            .set(buildKeyName(
                                                            environment.getProperty("spring.application.name"),
                                                            clientResponse.getClientId(),
                                                            userResponse.getUsername(),
                                                            request.getState()),
                                                    authDetails,
                                                    Duration.ofSeconds(localAppConfig.getAuth().getAuthCode().getExpirationTime())),
                                    Mono.just(AuthorizationResponse.builder()
                                            .authorizationCode(authCode)
                                            .state(request.getState())
                                            .build()
                                    )).handle(($$$, sink) -> {
                                if ($$$.getT1()) {
                                    sink.next($$$.getT2());
                                    return;
                                }
                                sink.error(new TechnicalException());
                            });
                        });
                    } else {
                        return Mono.error(new BadCredentialException("The provided password is wrong"));
                    }
                });
    }

    @Override
    public Mono<PKCEGrantTypeResponse> authenticate(PKCETokenRequest request) {
        String codeChallenge = CodeUtil.generateChallengeCode(request.getCodeVerifier());
        return redisOperations.opsForValue().get(buildKeyName(
                        environment.getProperty("spring.application.name"),
                        request.getClientId(),
                        request.getUsername(),
                        request.getState()))
                .switchIfEmpty(Mono.error(new UnsuccessfulAuthenticationException()))
                .handle(($, sink) -> {
                    if (!$.getAuthCode().equals(request.getAuthorizationCode())) {
                        sink.error(new InvalidAuthCodeException());
                        return;
                    }
                    if (!$.getCodeChallenge().equals(codeChallenge)) {
                        sink.error(new InvalidCodeVerifierException());
                        return;
                    }
                    LocalDateTime date = LocalDateTime.now();
                    Date accessExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getAccessToken().getExpirationTime());
                    Date refreshExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getRefreshToken().getExpirationTime());
                    sink.next(PKCEGrantTypeResponse.builder()
                            .accessToken(accessTokenPKCEService.buildToken($, accessExpirationDate))
                            .refreshToken(refreshTokenPKCEService.buildToken($, refreshExpirationDate))
                            .idToken(identityTokenPKCEService.buildToken($, accessExpirationDate))
                            .issuedAt(date)
                            .expiredAt(accessExpirationDate)
                            .build());
                });
    }

    @Override
    public Mono<PKCEGrantTypeResponse> refresh(RefreshTokenRequest request) {
        try {
            Claims claims = JWTsUtil.isTokenValid(request.getRefreshToken(), localAppConfig.getAuth().getKey());
            String clientId = (String) claims.get("clientId");
            String username = (String) claims.get("username");
            return Mono.zip(usersServiceClient.retrieveUserByUsername(username), clientsServiceClient.retrieveClientById(clientId))
                    .map($ -> AuthDetails.builder()
                            .user($.getT1())
                            .clientId($.getT2().getClientId())
                            .build())
                    .map($ -> {
                        LocalDateTime date = LocalDateTime.now();
                        Date accessExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getAccessToken().getExpirationTime());
                        Date refreshExpirationDate = DateUtil.plus(date, localAppConfig.getAuth().getRefreshToken().getExpirationTime());
                        return PKCEGrantTypeResponse.builder()
                                .accessToken(accessTokenPKCEService.buildToken($, accessExpirationDate))
                                .refreshToken(refreshTokenPKCEService.buildToken($, refreshExpirationDate))
                                .idToken(identityTokenPKCEService.buildToken($, accessExpirationDate))
                                .issuedAt(date)
                                .expiredAt(accessExpirationDate)
                                .build();
                    });
        } catch (ExpiredJwtException ex) {
            throw new ExpiredRefreshTokenException();
        } catch (Exception ex) {
            throw new InvalidRefreshTokenException();
        }
    }

    private static Mono<Boolean> validUser(UserResponse userResponse) {
        if (!userResponse.isEnabled()) {
            return Mono.error(new UserDisabledException());
        }
        if (userResponse.isLocked()) {
            return Mono.error(new UserLockedException());
        }
        if (userResponse.isCredentialExpired()) {
            return Mono.error(new UserCredentialsExpiredException());
        }
        if (userResponse.isAccountExpired()) {
            return Mono.error(new UserAccountExpiredException());
        }
        return Mono.just(true);
    }
}
