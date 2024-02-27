package com.selling.system.auth.manager.model.response.pkce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponse {

    @JsonProperty("authorization_code")
    private String authorizationCode;

    private String state;
}
