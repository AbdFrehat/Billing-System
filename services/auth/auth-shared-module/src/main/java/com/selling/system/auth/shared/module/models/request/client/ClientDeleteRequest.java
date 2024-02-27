package com.selling.system.auth.shared.module.models.request.client;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static com.selling.system.auth.shared.module.constants.Validator.CLIENT_ID_VALIDATOR_MESSAGE;

@Getter
public class ClientDeleteRequest {

    @NotBlank(message = CLIENT_ID_VALIDATOR_MESSAGE)
    private String clientId;
}
