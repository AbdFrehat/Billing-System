package com.selling.system.shared.module.exceptions.general;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{

    private final int statusCode;

    private final String message;

    public ClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

}
