package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class FieldTypeNotFoundException extends BusinessException {

    public FieldTypeNotFoundException(String message) {
        super(message);
    }
}
