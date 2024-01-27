package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class GroupDto {

    @JsonProperty("name")
    private String name;
}
