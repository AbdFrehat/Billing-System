package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
