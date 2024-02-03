package com.selling.system.auth.shared.module.models.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.USER_NAME_VALIDATOR_MESSAGE;

@Data
public class UserDeleteRequest {

    @NotBlank(message = USER_NAME_VALIDATOR_MESSAGE)
    private String username;
}
