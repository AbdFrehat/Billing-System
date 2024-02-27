package com.selling.system.auth.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Profile profile;
    private OffsetDateTime createdAt;
    private boolean enabled;
    private boolean accountExpired;
    private boolean credentialExpired;
    private boolean locked;
    private OffsetDateTime lastPasswordChanged;
    private String country;
    private String city;
    private String street;
}
