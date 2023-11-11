package com.selling.system.shared.models.exceptions;

public class PurchaseMethodNotFoundException extends RuntimeException {

    public PurchaseMethodNotFoundException(String message) {
        super(message);
    }
}
