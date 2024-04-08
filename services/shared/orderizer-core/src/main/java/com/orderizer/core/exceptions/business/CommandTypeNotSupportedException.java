package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class CommandTypeNotSupportedException extends BusinessException {

    public CommandTypeNotSupportedException(String message) {
        super(message);
    }
}
