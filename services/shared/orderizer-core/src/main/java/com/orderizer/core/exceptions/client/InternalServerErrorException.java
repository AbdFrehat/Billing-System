package com.orderizer.core.exceptions.client;

import com.orderizer.core.models.responses.ErrorResponse;
import com.orderizer.core.exceptions.general.ClientException;

public class InternalServerErrorException extends ClientException {

    public InternalServerErrorException(String message, int statusCode, ErrorResponse errorResponse) {
        super(message, statusCode, errorResponse);
    }

}
