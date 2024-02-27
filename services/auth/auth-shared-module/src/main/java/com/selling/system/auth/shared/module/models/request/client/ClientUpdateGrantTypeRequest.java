package com.selling.system.auth.shared.module.models.request.client;

import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import static com.selling.system.auth.shared.module.constants.Validator.*;

@Getter
public class ClientUpdateGrantTypeRequest {

    @NotBlank(message = CLIENT_ID_VALIDATOR_MESSAGE)
    private String clientId;

    @NotNull(message = GRANT_TYPE_VALIDATOR_MESSAGE)
    @ValidFieldTypeEnum(message = GRANT_TYPE_NOT_FOUND_MESSAGE, enumClass = GrantTypes.class)
    private String grantType;
}
