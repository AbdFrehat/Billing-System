package com.selling.system.auth.manager.model.client.response;

import com.selling.system.auth.manager.model.client.entities.Profile;
import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String email;
    private String password;
    private String phone;
    private Profile profile;
    private String createdAt;
    private boolean enabled;
    private boolean accountExpired;
    private boolean credentialExpired;
    private boolean locked;
    private String lastPasswordChanged;
    private String country;
    private String city;
    private String street;
}
