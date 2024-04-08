package com.orderizer.core.exceptions.client;

import com.orderizer.core.models.responses.ErrorResponse;
import com.orderizer.core.exceptions.general.ClientException;

public class UnauthorizedCallException extends ClientException {

    public UnauthorizedCallException(String message, int statusCode) {
        super(message, statusCode, null);
    }

    public UnauthorizedCallException(String message, int statusCode, ErrorResponse errorResponse) {
        super(message, statusCode, errorResponse);
    }
}
