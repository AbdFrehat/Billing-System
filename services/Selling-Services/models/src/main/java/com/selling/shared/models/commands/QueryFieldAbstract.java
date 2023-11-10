package com.selling.shared.models.commands;

import com.selling.shared.models.enums.FieldsType;

public interface QueryFieldAbstract {

    String getField();

    Object getValue();

    FieldsType getFieldsType();

}
