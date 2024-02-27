package com.selling.system.auth.manager.service.token.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.model.client.entities.Authority;
import com.selling.system.auth.manager.model.entities.AuthDetails;
import com.selling.system.auth.manager.model.enums.GrantTypes;
import com.selling.system.auth.manager.service.token.api.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.selling.system.auth.manager.constant.TokensConstants.ACCESS_TOKEN_SUBJECT;

@Service("pkceAccessToken")
@RequiredArgsConstructor
public class AccessTokenPKCEServiceImpl implements TokenService<AuthDetails> {

    private final LocalAppConfig localAppConfig;

    @Override
    public String buildToken(AuthDetails authDetails, Date expirationDateTime) {
        return Jwts.builder()
                .setIssuer(authDetails.getUser().getUsername())
                .setSubject(ACCESS_TOKEN_SUBJECT)
                .setExpiration(expirationDateTime)
                .claim("clientId", authDetails.getClientId())
                .claim("username", authDetails.getUser().getUsername())
                .claim("grantType", GrantTypes.PKCE)
                .claim("profile", authDetails.getUser().getProfile().getProfileName())
                .claim("authorities", authDetails.getUser().getProfile().getAuthorities().values().stream().flatMap(Collection::stream).map(Authority::getAuthorityName).toList())
                .signWith(Keys.hmacShaKeyFor(localAppConfig.getAuth()
                        .getKey()
                        .getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
