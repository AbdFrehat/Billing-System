package com.selling.system.auth.shared.module.models.request.authority;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.AUTHORITY_NAME_VALIDATOR_MESSAGE;

@Data
public class AuthorityDeleteRequest {

    @NotBlank(message = AUTHORITY_NAME_VALIDATOR_MESSAGE)
    private String authorityName;
}
