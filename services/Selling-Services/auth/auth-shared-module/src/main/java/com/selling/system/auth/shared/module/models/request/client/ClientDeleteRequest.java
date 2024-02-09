package com.selling.system.auth.shared.module.models.request.client;

import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import static com.selling.system.auth.shared.module.constants.Validator.*;

@Getter
public class ClientDeleteRequest {

    @NotBlank(message = CLIENT_ID_VALIDATOR_MESSAGE)
    private String clientId;
}
