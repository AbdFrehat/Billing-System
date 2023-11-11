package com.selling.system.shared.models.commands;

import com.selling.system.shared.models.enums.FieldsType;

public interface QueryFieldAbstract {

    String getField();

    Object getValue();

    FieldsType getFieldsType();

}
