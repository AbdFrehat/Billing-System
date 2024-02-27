package com.selling.system.auth.shared.module.models.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.*;

@Data
public class UserUpdateInfoRequest {

    private String username;

    @NotBlank(message = USER_NAME_VALIDATOR_MESSAGE)
    @Size(message = USER_NAME_SIZE_VALIDATOR_MESSAGE, min = 8)
    private String updatedUsername;

    @Email(message = EMAIL_VALIDATOR_MESSAGE)
    private String email;

    @NotBlank(message = PHONE_NUMBER_VALIDATOR_MESSAGE)
    private String phone;

    @NotBlank(message = COUNTRY_VALIDATOR_MESSAGE)
    private String country;

    @NotBlank(message = CITY_VALIDATOR_MESSAGE)
    private String city;

    @NotBlank(message = STREET_VALIDATOR_MESSAGE)
    private String street;

}
