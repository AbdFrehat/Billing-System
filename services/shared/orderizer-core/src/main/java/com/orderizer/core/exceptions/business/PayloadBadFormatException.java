package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class PayloadBadFormatException extends BusinessException {

    public PayloadBadFormatException(String message) {
        super(message);
    }
}
