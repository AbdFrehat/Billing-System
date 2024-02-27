package com.selling.system.auth.manager.model.client.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {

    @JsonProperty("name")
    private String profileName;

    @JsonProperty("authorities")
    private Map<String, Set<Authority>> authorities;

}
