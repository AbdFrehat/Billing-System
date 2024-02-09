package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;
import com.selling.system.shared.module.models.responses.ErrorResponse;

public class UnauthorizedCallException extends ClientException {

    public UnauthorizedCallException(String message, int statusCode) {
        super(message, statusCode, null);
    }

    public UnauthorizedCallException(String message, int statusCode, ErrorResponse errorResponse) {
        super(message, statusCode, errorResponse);
    }
}
