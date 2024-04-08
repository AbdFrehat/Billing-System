package com.orderizer.core.models.commands;

import com.orderizer.core.models.enums.FieldType;

public interface QueryFieldAbstract {

    String getField();

    Object getValue();

    FieldType getFieldType();

}
