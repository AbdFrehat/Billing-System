package com.selling.system.shared.module.exceptions.business;

public class CommandTypeNotSupportedException extends RuntimeException {

    public CommandTypeNotSupportedException(String message) {
        super(message);
    }
}
