package com.selling.system.auth.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private int clientSeq;

    private String clientName;

    private String clientId;

    private String clientSecret;

    private Profile profile;

    private GrantType grantType;

}
