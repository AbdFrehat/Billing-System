package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class PayloadBadFormatException extends BusinessException {

    public PayloadBadFormatException(String message) {
        super(message);
    }
}
