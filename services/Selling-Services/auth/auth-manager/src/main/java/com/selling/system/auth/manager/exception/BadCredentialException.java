package com.selling.system.auth.manager.exception;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class BadCredentialException extends BusinessException {

    public BadCredentialException(String message) {
        super(message);
    }
}
