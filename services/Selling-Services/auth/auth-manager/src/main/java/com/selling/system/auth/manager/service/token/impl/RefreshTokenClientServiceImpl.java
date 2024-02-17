package com.selling.system.auth.manager.service.token.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.auth.manager.service.token.api.AccessTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.selling.system.auth.manager.constant.TokensConstants.ACCESS_TOKEN_SUBJECT;

@Service("clientRefreshToken")
@RequiredArgsConstructor
public class RefreshTokenClientServiceImpl implements AccessTokenService<ClientResponse> {

    private final LocalAppConfig localAppConfig;

    @Override
    public String buildToken(ClientResponse clientResponse) {
        return Jwts.builder()
                .setIssuer(clientResponse.getClientId())
                .setSubject(ACCESS_TOKEN_SUBJECT)
                .claim("clientId", clientResponse.getClientId())
                .claim("grantType", clientResponse.getGrantTypes())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + localAppConfig.getAuth().getRefreshToken().getExpirationTime() * 1000))
                .signWith(Keys.hmacShaKeyFor(localAppConfig.getAuth()
                        .getKey()
                        .getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
