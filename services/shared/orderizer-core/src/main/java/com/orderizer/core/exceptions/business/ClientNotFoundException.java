package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class ClientNotFoundException extends BusinessException {

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
