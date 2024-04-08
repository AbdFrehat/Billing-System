package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
