package com.selling.system.auth.shared.module.models.request.client;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static com.selling.system.auth.shared.module.constants.Validator.CLIENT_ID_VALIDATOR_MESSAGE;
import static com.selling.system.auth.shared.module.constants.Validator.PROFILE_NAME_VALIDATOR_MESSAGE;

@Getter
public class ClientUpdateProfileRequest {

    @NotBlank(message = CLIENT_ID_VALIDATOR_MESSAGE)
    private String clientId;

    @NotBlank(message = PROFILE_NAME_VALIDATOR_MESSAGE)
    private String profileName;

}
