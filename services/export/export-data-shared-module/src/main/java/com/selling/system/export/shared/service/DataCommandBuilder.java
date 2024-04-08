package com.selling.system.export.shared.service;

import com.orderizer.core.models.commands.DataCommand;
import com.orderizer.core.models.commands.ExportDataFilter;

public interface DataCommandBuilder {

    DataCommand build(ExportDataFilter exportDataFilter);
}
