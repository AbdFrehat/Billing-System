package com.selling.system.auth.shared.module.models.request.user;

import com.selling.system.auth.shared.module.models.enums.FlagType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

import static com.selling.system.auth.shared.module.constants.Validator.FLAG_NAME_VALIDATOR_MESSAGE;
import static com.selling.system.auth.shared.module.constants.Validator.USER_NAME_VALIDATOR_MESSAGE;

@Data
public class UserUpdateFlagsRequest implements Serializable {

    @NotNull(message = FLAG_NAME_VALIDATOR_MESSAGE)
    private FlagType flagType;

    @NotBlank(message = USER_NAME_VALIDATOR_MESSAGE)
    private String username;

    private Boolean flag;
}
