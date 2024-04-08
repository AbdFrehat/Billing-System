package com.orderizer.core.exceptions.Technical;

import com.orderizer.core.exceptions.general.TechnicalException;

public class QueryParamsMissingException extends TechnicalException {

    public QueryParamsMissingException(String message) {
        super(message);
    }
}
