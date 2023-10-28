package com.selling.shared.models.enums;

public enum PurchaseMethod {

    IN_STORE("In store"),
    PHONE("Phone"),
    ONLINE("Online");

    PurchaseMethod(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
