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

import static com.selling.system.auth.manager.constant.TokensConstants.IDENTITY_TOKEN_SUBJECT;

@Service("pkceIdentityToken")
@RequiredArgsConstructor
public class IdentityTokenPKCEServiceImpl implements TokenService<AuthDetails> {

    private final LocalAppConfig localAppConfig;


    @Override
    public String buildToken(AuthDetails authDetails, Date expirationDateTime) {
        return Jwts.builder()
                .setIssuer(authDetails.getUser().getUsername())
                .setSubject(IDENTITY_TOKEN_SUBJECT)
                .setExpiration(expirationDateTime)
                .claim("clientId", authDetails.getClientId())
                .claim("username", authDetails.getUser().getUsername())
                .claim("grantType", GrantTypes.PKCE)
                .claim("grantType", GrantTypes.PKCE)
                .claim("profile", authDetails.getUser().getProfile().getProfileName())
                .claim("email", authDetails.getUser().getEmail())
                .claim("country", authDetails.getUser().getCountry())
                .claim("city", authDetails.getUser().getCity())
                .claim("street", authDetails.getUser().getStreet())
                .claim("phone", authDetails.getUser().getPhone())
                .claim("createdAt", authDetails.getUser().getCreatedAt())
                .signWith(Keys.hmacShaKeyFor(localAppConfig.getAuth()
                        .getKey()
                        .getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
