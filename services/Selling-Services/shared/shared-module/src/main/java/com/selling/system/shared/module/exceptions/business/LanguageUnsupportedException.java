package com.selling.system.shared.module.exceptions.business;

import com.selling.system.shared.module.exceptions.general.BusinessException;

public class LanguageUnsupportedException extends BusinessException {

    public LanguageUnsupportedException() {
    }

    public LanguageUnsupportedException(String message) {
        super(message);
    }
}
