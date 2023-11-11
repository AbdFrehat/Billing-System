package com.selling.system.shared.models.commands;

import com.selling.system.shared.models.enums.FieldType;

public interface QueryFieldAbstract {

    String getField();

    Object getValue();

    FieldType getFieldType();

}
