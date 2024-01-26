package com.selling.system.auth.shared.module.models.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class ProfileInsertRequest {

    @NotBlank(message = "profile name can not be empty")
    private String profileName;

    @Min(value = 1, message = "At least one authority should be assigned to the profile")
    private Set<String> authorities;

}
