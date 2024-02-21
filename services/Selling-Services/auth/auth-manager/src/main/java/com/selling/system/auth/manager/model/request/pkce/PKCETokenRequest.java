package com.selling.system.auth.manager.model.request.pkce;

import lombok.Data;

@Data
public class PKCETokenRequest {

    private String authorizationCode;
    private String clientId;
    private String username;
    private String codeVerifier;
    private String state;
}
