package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

/**
 * This exception class is thrown when an eny exception is thrown while converting an object to another type.
 * @author Abd Frehat
 * @since 1.0
 */
public class BadConvertorException extends BusinessException {

    public BadConvertorException(String message) {
        super(message);
    }
}
