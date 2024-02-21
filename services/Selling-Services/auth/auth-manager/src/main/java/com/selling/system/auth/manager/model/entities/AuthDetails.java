package com.selling.system.auth.manager.model.entities;

import com.selling.system.auth.manager.model.client.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDetails {

    private String authCode;

    private String clientId;

    private String codeChallenge;

    private UserResponse user;

}
