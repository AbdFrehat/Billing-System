package com.selling.system.auth.manager.model.client.response;

import com.selling.system.auth.manager.model.client.entities.Profile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {

    private String clientId;

    private String clientName;

    private String clientSecret;

    private Profile profile;

    private String grantTypes;
}
