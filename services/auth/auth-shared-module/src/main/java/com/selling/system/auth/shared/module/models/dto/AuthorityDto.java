package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class AuthorityDto {

    @JsonProperty("name")
    private String authorityName;

    @JsonIgnore
    private String groupName;

}
