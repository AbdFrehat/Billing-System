package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class PasswordNotMatchedException extends BusinessException {

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
