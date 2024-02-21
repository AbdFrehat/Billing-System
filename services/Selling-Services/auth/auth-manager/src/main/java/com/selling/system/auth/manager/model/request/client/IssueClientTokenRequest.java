package com.selling.system.auth.manager.model.request.client;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.selling.system.auth.manager.constant.Validator.CLIENT_ID_VALIDATOR_MESSAGE;
import static com.selling.system.auth.manager.constant.Validator.CLIENT_SECRET_VALIDATOR_MESSAGE;

@Data
public class IssueClientTokenRequest {

    @NotNull(message = CLIENT_ID_VALIDATOR_MESSAGE)
    private String clientId;

    @NotNull(message = CLIENT_SECRET_VALIDATOR_MESSAGE)
    private String clientSecret;

}
