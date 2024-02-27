package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class PurchaseMethodNotFoundException extends BusinessException {

    public PurchaseMethodNotFoundException(String message) {
        super(message);
    }
}
