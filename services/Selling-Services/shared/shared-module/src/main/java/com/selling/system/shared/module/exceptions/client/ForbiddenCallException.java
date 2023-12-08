package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class ForbiddenCallException extends ClientException {

    public ForbiddenCallException(String message, int statusCode) {
        super(message, statusCode);
    }

}
