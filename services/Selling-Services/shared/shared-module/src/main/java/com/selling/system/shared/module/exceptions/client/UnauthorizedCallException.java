package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class UnauthorizedCallException extends ClientException {

    public UnauthorizedCallException(String message, int statusCode) {
        super(message, statusCode);
    }

}
