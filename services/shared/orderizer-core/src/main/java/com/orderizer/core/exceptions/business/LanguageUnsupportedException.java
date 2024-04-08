package com.orderizer.core.exceptions.business;

import com.orderizer.core.exceptions.general.BusinessException;

public class LanguageUnsupportedException extends BusinessException {

    public LanguageUnsupportedException() {
    }

    public LanguageUnsupportedException(String message) {
        super(message);
    }
}
