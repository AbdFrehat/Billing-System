package com.selling.system.shared.module.exceptions;

/**
 * This exception class is thrown when an eny exception is thrown while converting an object to another type.
 * @author Abd Frehat
 * @since 1.0
 */
public class BadConvertorException extends RuntimeException {

    public BadConvertorException(String message) {
        super(message);
    }
}
