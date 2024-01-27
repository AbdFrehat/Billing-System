package com.selling.system.auth.shared.module.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Map;
import java.util.Set;

@Builder
public class ProfileDto {

    @JsonProperty("name")
    private String profileName;

    @JsonProperty("authorities")
    private Map<String, Set<AuthorityDto>> authorities;

}
