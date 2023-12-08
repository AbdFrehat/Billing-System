package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class ServiceUnAvailableException extends ClientException {

    public ServiceUnAvailableException(String message, int statusCode) {
        super(message, statusCode);
    }

}
