package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class FlagTypeNotFoundException extends BusinessException {

    public FlagTypeNotFoundException(String message) {
        super(message);
    }
}
