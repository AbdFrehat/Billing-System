package com.orderizer.core.models.commands;

import com.orderizer.core.models.enums.ExportType;

public interface ExportDataCommandAbstract {

    ExportDataFilter getExportDataFilter();

    String getFileName();

    ExportType getExportType();
}
