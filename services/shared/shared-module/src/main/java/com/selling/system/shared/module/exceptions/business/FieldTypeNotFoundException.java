package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class FieldTypeNotFoundException extends BusinessException {

    public FieldTypeNotFoundException(String message) {
        super(message);
    }
}
