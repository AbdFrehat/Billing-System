package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class AuthorityNotFoundException extends BusinessException {

    public AuthorityNotFoundException() {
    }

    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
