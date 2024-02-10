package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.ExportType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExportDataCommand implements ExportDataCommandAbstract {

    @Valid
    private ExportDataFilter exportDataFilter;

    @NotBlank
    private String fileName;

    @NotNull(message = "export type must not be null")
    private ExportType exportType;
}
