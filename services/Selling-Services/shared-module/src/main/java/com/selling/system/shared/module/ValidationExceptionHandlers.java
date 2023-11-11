package com.selling.system.shared.module;

import com.selling.system.shared.models.exceptions.BadConvertorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ValidationExceptionHandlers {


    @ExceptionHandler(BadConvertorException.class)
    public ResponseEntity<String> handleBadExceptionHandler(BadConvertorException ex) {
        return null;
    }

}
