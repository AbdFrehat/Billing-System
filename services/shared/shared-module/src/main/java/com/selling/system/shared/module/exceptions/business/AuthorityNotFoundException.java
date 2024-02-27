package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class AuthorityNotFoundException extends BusinessException {

    public AuthorityNotFoundException() {
    }

    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
