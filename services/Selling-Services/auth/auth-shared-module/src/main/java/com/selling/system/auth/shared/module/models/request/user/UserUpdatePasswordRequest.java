package com.selling.system.auth.shared.module.models.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.PASSWORD_SIZE_VALIDATOR_MESSAGE;
import static com.selling.system.auth.shared.module.constants.Validator.PASSWORD_VALIDATOR_MESSAGE;

@Data
public class UserUpdatePasswordRequest {

    private String username;

    @NotBlank(message = PASSWORD_VALIDATOR_MESSAGE)
    @Size(message = PASSWORD_SIZE_VALIDATOR_MESSAGE, min = 8)
    private String oldPassword;

    @NotBlank(message = PASSWORD_VALIDATOR_MESSAGE)
    @Size(message = PASSWORD_SIZE_VALIDATOR_MESSAGE, min = 8)
    private String newPassword;
}
