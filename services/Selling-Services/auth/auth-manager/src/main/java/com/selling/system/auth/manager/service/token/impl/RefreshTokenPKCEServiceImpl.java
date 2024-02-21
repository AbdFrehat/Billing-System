package com.selling.system.auth.manager.service.token.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.model.entities.AuthDetails;
import com.selling.system.auth.manager.model.enums.GrantTypes;
import com.selling.system.auth.manager.service.token.api.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.selling.system.auth.manager.constant.TokensConstants.REFRESH_TOKEN_SUBJECT;

@Service("pkceRefreshToken")
@RequiredArgsConstructor
public class RefreshTokenPKCEServiceImpl implements TokenService<AuthDetails> {

    private final LocalAppConfig localAppConfig;

    @Override
    public String buildToken(AuthDetails authDetails, Date expirationDateTime) {
        return Jwts.builder()
                .setIssuer(authDetails.getClientId())
                .setSubject(REFRESH_TOKEN_SUBJECT)
                .claim("clientId", authDetails.getClientId())
                .claim("username", authDetails.getUser().getUsername())
                .claim("grantType", GrantTypes.PKCE)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + localAppConfig.getAuth().getRefreshToken().getExpirationTime() * 1000))
                .signWith(Keys.hmacShaKeyFor(localAppConfig.getAuth()
                        .getKey()
                        .getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
