package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.ExportType;

public interface ExportDataCommandAbstract {

    ExportDataFilter getExportDataFilter();

    String getFileName();

    ExportType getExportType();
}
