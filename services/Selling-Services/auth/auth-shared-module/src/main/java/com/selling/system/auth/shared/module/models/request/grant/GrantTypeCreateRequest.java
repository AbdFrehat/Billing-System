package com.selling.system.auth.shared.module.models.request.grant;

import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import static com.selling.system.auth.shared.module.constants.Validator.GRANT_TYPE_NOT_FOUND_MESSAGE;
import static com.selling.system.auth.shared.module.constants.Validator.GRANT_TYPE_VALIDATOR_MESSAGE;

@Getter
public class GrantTypeCreateRequest {

    @NotNull(message = GRANT_TYPE_VALIDATOR_MESSAGE)
    @ValidFieldTypeEnum(message = GRANT_TYPE_NOT_FOUND_MESSAGE, enumClass = GrantTypes.class)
    private String grantTypes;
}
