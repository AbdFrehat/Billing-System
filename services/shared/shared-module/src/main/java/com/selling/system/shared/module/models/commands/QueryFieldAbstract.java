package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.FieldType;

public interface QueryFieldAbstract {

    String getField();

    Object getValue();

    FieldType getFieldType();

}
