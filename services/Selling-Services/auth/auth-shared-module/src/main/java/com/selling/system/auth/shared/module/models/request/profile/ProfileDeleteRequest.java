package com.selling.system.auth.shared.module.models.request.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.PROFILE_NAME_VALIDATOR_MESSAGE;

@Data
public class ProfileDeleteRequest {

    @NotBlank(message = PROFILE_NAME_VALIDATOR_MESSAGE)
    private String profileName;
}
