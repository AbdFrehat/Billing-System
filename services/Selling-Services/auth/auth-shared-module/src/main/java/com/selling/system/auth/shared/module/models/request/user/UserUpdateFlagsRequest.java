package com.selling.system.auth.shared.module.models.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.selling.system.auth.shared.module.models.enums.FlagType;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

import static com.selling.system.auth.shared.module.constants.Validator.*;

@Data
public class UserUpdateFlagsRequest implements Serializable {

    @NotNull(message = FLAG_NAME_VALIDATOR_MESSAGE)
    @ValidFieldTypeEnum(message = FLAG_NAME_NOT_FOUND_MESSAGE, regexp = "ENABLE|LOCK|EXPIRE_ACCOUNT|EXPIRE_CREDENTIAL")
    private FlagType flagType;

    @NotBlank(message = USER_NAME_VALIDATOR_MESSAGE)
    private String username;

    private Boolean flag;
}
