package com.selling.system.shared.models.commands;

import java.util.List;

public interface QueryCommandAbstract {

    List<? extends QueryFieldAbstract> getQueryFields();

}
