package com.orderizer.core.exceptions.Technical;

import com.orderizer.core.exceptions.general.TechnicalException;

public class QueryNotFoundException extends TechnicalException {

    public QueryNotFoundException(String message) {
        super(message);
    }
}
