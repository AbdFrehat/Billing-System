package com.selling.system.auth.shared.module.models.request.group;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.GROUP_NAME_VALIDATOR_MESSAGE;

@Data
public class GroupInsertRequest {

    @NotBlank(message = GROUP_NAME_VALIDATOR_MESSAGE)
    private String groupName;
}
