package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class PurchaseMethodNotFoundException extends BusinessException {

    public PurchaseMethodNotFoundException(String message) {
        super(message);
    }
}
