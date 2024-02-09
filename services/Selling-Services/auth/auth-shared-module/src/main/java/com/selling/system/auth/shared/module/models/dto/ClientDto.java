package com.selling.system.auth.shared.module.models.dto;

import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String clientId;

    private String clientName;

    private String clientSecret;

    private ProfileDto profileDto;

    private GrantTypes grantTypes;
}
