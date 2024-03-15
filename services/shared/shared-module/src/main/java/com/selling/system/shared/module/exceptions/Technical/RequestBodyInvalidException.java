package com.selling.system.shared.module.exceptions.Technical;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class RequestBodyInvalidException extends BusinessException {

    public RequestBodyInvalidException(String message) {
        super(message);
    }
}
