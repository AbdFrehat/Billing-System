package com.orderizer.core.models.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SortField {

    @JsonProperty(value = "direction")
    @NotBlank(message = "SortField.direction can not be empty")
    @Pattern(regexp = "ASC|DESC", message = "SortField.field can be ASC or DESC")
    private String direction;

    @JsonProperty(value = "field")
    @NotBlank(message = "SortField.field can not be empty")
    private String field;
}
