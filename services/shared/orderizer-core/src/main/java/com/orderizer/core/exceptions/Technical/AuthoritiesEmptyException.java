package com.orderizer.core.exceptions.Technical;

import com.orderizer.core.exceptions.general.TechnicalException;

public class AuthoritiesEmptyException extends TechnicalException {

    public AuthoritiesEmptyException(String message) {
        super(message);
    }


    public AuthoritiesEmptyException() {
    }
}
