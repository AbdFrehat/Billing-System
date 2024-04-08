package com.selling.system.auth.manager.exception;

import com.orderizer.core.exceptions.general.BusinessException;

public class BadCredentialException extends BusinessException {

    public BadCredentialException(String message) {
        super(message);
    }
}
