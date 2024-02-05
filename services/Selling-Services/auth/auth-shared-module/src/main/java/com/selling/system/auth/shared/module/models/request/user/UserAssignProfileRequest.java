package com.selling.system.auth.shared.module.models.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.PROFILE_NAME_VALIDATOR_MESSAGE;

@Data
public class UserAssignProfileRequest {

    private String username;

    @NotBlank(message = PROFILE_NAME_VALIDATOR_MESSAGE)
    private String profileName;
}
