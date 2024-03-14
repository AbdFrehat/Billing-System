package com.selling.system.shared.module.exceptions.Technical;

import com.selling.system.shared.module.exceptions.general.TechnicalException;

public class QueryParamsMissingException extends TechnicalException {

    public QueryParamsMissingException(String message) {
        super(message);
    }
}
