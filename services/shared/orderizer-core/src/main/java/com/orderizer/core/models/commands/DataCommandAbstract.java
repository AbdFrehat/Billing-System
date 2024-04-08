package com.orderizer.core.models.commands;

import com.orderizer.core.models.enums.CommandType;

import java.util.Map;

public interface DataCommandAbstract {

    Map<String, ? extends QueryFieldAbstract> getQueryFields();

    int getPage();

    int getSize();

    CommandType getCommandType();

    Object getPayload();

}
