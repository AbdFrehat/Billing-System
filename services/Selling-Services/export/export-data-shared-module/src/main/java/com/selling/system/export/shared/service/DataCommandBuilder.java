package com.selling.system.export.shared.service;

import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.ExportDataFilter;

public interface DataCommandBuilder {

    DataCommand build(ExportDataFilter exportDataFilter);
}
