package com.selling.system.shared.models.exceptions;

public class BadConvertorException extends RuntimeException {

    public static final int ERROR_CODE = 1000;

    public static final String EXCEPTION_NAME = BadConvertorException.class.getSimpleName();

    public BadConvertorException(String message) {
        super(message);
    }
}
