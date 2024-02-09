package com.selling.system.shared.module.exceptions.client;

import com.selling.system.shared.module.exceptions.general.ClientException;
import com.selling.system.shared.module.models.responses.ErrorResponse;

public class InternalServerErrorException extends ClientException {

    public InternalServerErrorException(String message, int statusCode, ErrorResponse errorResponse) {
        super(message, statusCode, errorResponse);
    }

}
