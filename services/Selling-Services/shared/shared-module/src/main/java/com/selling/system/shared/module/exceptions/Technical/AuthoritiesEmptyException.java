package com.selling.system.shared.module.exceptions.Technical;

import com.selling.system.shared.module.exceptions.general.TechnicalException;

public class AuthoritiesEmptyException extends TechnicalException {

    public AuthoritiesEmptyException(String message) {
        super(message);
    }


    public AuthoritiesEmptyException() {
    }
}
