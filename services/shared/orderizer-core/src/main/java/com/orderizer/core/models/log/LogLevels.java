package com.orderizer.core.models.log;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogLevels {

    @Size(min = 1, message = "At least one log level should be provided.")
    private List<@Valid LogLevel> logLevels;
}
