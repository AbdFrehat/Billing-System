package com.orderizer.core.exceptions.client;

import com.orderizer.core.models.responses.ErrorResponse;
import com.orderizer.core.exceptions.general.ClientException;

public class BadRequestException extends ClientException {


    public BadRequestException(String message, int statusCode, ErrorResponse errorResponse) {
        super(message, statusCode, errorResponse);
    }

    public BadRequestException(String message, int statusCode) {
        super(message, statusCode, null);
    }
}
