package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class ProfileNotFoundException extends BusinessException {

    public ProfileNotFoundException() {
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
