package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class FlagTypeNotFoundException extends BusinessException {

    public FlagTypeNotFoundException(String message) {
        super(message);
    }
}
