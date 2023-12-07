package com.selling.system.shared.module.models.log;

import ch.qos.logback.classic.Level;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogLevel {

    @JsonProperty(value = "package")
    @NotBlank(message = "The package path should not be empty")
    private String packagePath;

    @JsonProperty(value = "level")
    @NotNull
    private Level level;

}
