package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.CommandType;

import java.util.Map;

public interface QueryCommandAbstract {

    Map<String, ? extends QueryFieldAbstract> getQueryFields();

    int getPage();

    int getSize();

    CommandType getCommandType();

    Object getPayload();

}
