package com.selling.system.auth.shared.module.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
public class GroupsDto {

    @JsonProperty("groups")
    private List<GroupDto> groups;
}
