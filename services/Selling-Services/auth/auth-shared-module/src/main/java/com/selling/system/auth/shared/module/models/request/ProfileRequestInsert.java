package com.selling.system.auth.shared.module.models.request;

import lombok.Data;

import java.util.Set;

@Data
public class ProfileRequestInsert {

    private String profileName;

    private Set<String> authorities;

}
