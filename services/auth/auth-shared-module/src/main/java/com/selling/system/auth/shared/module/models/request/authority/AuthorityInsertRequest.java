package com.selling.system.auth.shared.module.models.request.authority;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Validator.AUTHORITIES_SIZE_VALIDATOR_MESSAGE;

@Data
public class AuthorityInsertRequest {

    @Size(min = 1, message = AUTHORITIES_SIZE_VALIDATOR_MESSAGE)
    Set<@Valid AuthorityData> authorities;

}
