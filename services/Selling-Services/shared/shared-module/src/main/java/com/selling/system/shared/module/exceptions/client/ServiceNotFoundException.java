package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;

public class ServiceNotFoundException extends ClientException {

    public ServiceNotFoundException(String message, int statusCode) {
        super(message, statusCode);
    }

}
