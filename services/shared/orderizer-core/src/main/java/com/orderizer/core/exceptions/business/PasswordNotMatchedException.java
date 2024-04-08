package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class PasswordNotMatchedException extends BusinessException {

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
