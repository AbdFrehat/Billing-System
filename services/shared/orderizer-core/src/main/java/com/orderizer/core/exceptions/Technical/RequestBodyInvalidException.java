package com.orderizer.core.exceptions.Technical;

import com.orderizer.core.exceptions.general.BusinessException;

public class RequestBodyInvalidException extends BusinessException {

    public RequestBodyInvalidException(String message) {
        super(message);
    }
}
