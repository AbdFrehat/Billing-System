package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.QueryMethod;

import java.util.List;

public interface QueryCommandAbstract {

    List<? extends QueryFieldAbstract> getQueryFields();

    int getPage();

    int getSize();

    QueryMethod getQueryMethod();

    Object getPayload();

}
