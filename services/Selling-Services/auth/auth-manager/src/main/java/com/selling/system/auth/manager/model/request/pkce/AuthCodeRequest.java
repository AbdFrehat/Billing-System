package com.selling.system.auth.manager.model.request.pkce;

import lombok.Data;

@Data
public class AuthCodeRequest {

    private String clientId;

    private String username;

    private String password;

    private String codeChallenge;

    private String state;

}
