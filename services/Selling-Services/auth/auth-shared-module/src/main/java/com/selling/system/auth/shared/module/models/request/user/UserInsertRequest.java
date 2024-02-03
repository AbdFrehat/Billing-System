package com.selling.system.auth.shared.module.models.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.selling.system.auth.shared.module.constants.Validator.*;

@Data
public class UserInsertRequest {
    @NotBlank(message = USER_NAME_VALIDATOR_MESSAGE)
    @Size(message = USER_NAME_SIZE_VALIDATOR_MESSAGE, min = 8)
    private String username;

    @Email(message = EMAIL_VALIDATOR_MESSAGE)
    private String email;

    @NotBlank(message = PASSWORD_VALIDATOR_MESSAGE)
    @Size(message = PASSWORD_SIZE_VALIDATOR_MESSAGE, min = 8)
    private String password;

    @NotBlank(message = PHONE_NUMBER_VALIDATOR_MESSAGE)
    private String phone;

    @NotBlank(message = PROFILE_NAME_VALIDATOR_MESSAGE)
    private String profileName;

    @NotBlank(message = COUNTRY_VALIDATOR_MESSAGE)
    private String country;

    @NotBlank(message = CITY_VALIDATOR_MESSAGE)
    private String city;

    @NotBlank(message = STREET_VALIDATOR_MESSAGE)
    private String street;

}


