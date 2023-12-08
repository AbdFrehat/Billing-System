package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class BadRequestException extends ClientException {

    public BadRequestException(String message, int statusCode) {
        super(message, statusCode);
    }

}
