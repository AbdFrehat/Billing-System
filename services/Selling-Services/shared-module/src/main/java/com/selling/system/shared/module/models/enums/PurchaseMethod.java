package com.selling.system.shared.module.models.enums;

import lombok.Getter;

@Getter
public enum PurchaseMethod {

    IN_STORE("In store"),
    PHONE("Phone"),
    ONLINE("Online");

    PurchaseMethod(String value) {
        this.value = value;
    }

    private final String value;

}
