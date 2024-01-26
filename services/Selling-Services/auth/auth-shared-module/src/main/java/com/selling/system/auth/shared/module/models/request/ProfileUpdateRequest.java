package com.selling.system.auth.shared.module.models.request;

import lombok.Data;

import java.util.Set;

@Data
public class ProfileUpdateRequest {

    private String name;

    private String updatedName;

    private Set<String> addedAuthorities;

    private Set<String> removedAuthorities;

}
