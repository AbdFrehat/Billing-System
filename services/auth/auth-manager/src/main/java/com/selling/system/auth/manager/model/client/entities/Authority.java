package com.selling.system.auth.manager.model.client.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Authority {

    @JsonProperty("name")
    private String authorityName;

}
