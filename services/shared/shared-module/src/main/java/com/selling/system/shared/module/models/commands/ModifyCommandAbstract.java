package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.CommandType;

public interface ModifyCommandAbstract {

    CommandType getCommandType();

    Object getData();
}
