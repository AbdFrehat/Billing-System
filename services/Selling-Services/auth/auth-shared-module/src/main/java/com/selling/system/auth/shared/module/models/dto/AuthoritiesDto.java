package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
public class AuthoritiesDto {

    @JsonProperty("authorities")
    private Map<String, Set<AuthorityDto>> authorities;

}
