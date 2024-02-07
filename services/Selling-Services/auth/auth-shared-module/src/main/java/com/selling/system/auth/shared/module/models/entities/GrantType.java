package com.selling.system.auth.shared.module.models.entities;

import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrantType {

    private int grantId;

    private GrantTypes grantTypes;
}
