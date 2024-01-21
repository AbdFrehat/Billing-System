package com.selling.system.shared.module.exceptions.Technical;

import com.selling.system.shared.module.exceptions.general.TechnicalException;

public class QueryNotFoundException extends TechnicalException {

    public QueryNotFoundException(String message) {
        super(message);
    }
}
