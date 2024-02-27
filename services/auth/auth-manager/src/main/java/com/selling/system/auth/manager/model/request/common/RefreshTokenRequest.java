package com.selling.system.auth.manager.model.request.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.selling.system.auth.manager.constant.Validator.REFRESH_TOKEN_VALIDATOR_MESSAGE;

@Data
public class RefreshTokenRequest {

    @NotNull(message = REFRESH_TOKEN_VALIDATOR_MESSAGE)
    private String refreshToken;

}
