package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String password;
    private String phone;
    private ProfileDto profile;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime createdAt;
    private boolean enabled;
    private boolean accountExpired;
    private boolean credentialExpired;
    private boolean locked;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime lastPasswordChanged;
    private String country;
    private String city;
    private String street;
}
