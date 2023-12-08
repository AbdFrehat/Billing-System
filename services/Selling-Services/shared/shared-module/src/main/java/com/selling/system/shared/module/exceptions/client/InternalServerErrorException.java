package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class InternalServerErrorException extends ClientException {

    public InternalServerErrorException(String message, int statusCode) {
        super(message, statusCode);
    }

}
