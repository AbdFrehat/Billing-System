package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorityDto {

    @JsonProperty("name")
    private String authorityName;

}
