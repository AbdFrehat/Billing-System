package com.selling.system.shared.module.exceptions.general;

import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {


    private final String message;

    public TechnicalException(String message) {
        super(message);
        this.message = message;
    }
}
