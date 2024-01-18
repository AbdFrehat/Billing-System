package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class CommandTypeNotSupportedException extends BusinessException {

    public CommandTypeNotSupportedException(String message) {
        super(message);
    }
}
