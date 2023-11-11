package com.selling.system.shared.module.models.commands;

import java.util.List;

public interface QueryCommandAbstract {

    List<? extends QueryFieldAbstract> getQueryFields();

    int getPage();

    int getSize();

}
