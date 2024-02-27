package com.selling.system.auth.shared.module.models.request.authority;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.AUTHORITY_NAME_VALIDATOR_MESSAGE;
import static com.selling.system.auth.shared.module.constants.Validator.GROUP_NAME_VALIDATOR_MESSAGE;

@Data
public class AuthorityData {

    @NotBlank(message = GROUP_NAME_VALIDATOR_MESSAGE)
    private String groupName;

    @NotBlank(message = AUTHORITY_NAME_VALIDATOR_MESSAGE)
    private String authorityName;

}
