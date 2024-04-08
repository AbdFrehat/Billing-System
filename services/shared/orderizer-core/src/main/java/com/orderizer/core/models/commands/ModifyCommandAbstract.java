package com.orderizer.core.models.commands;

import com.orderizer.core.models.enums.CommandType;

public interface ModifyCommandAbstract {

    CommandType getCommandType();

    Object getData();
}
