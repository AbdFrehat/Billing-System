package com.selling.system.shared.module.exceptions.handlers;

import com.selling.system.shared.models.exceptions.BadConvertorException;
import com.selling.system.shared.module.modles.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ValidationExceptionHandlers {


    @ExceptionHandler(BadConvertorException.class)
    public ResponseEntity<ErrorResponse> handleBadConvertorException(BadConvertorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(BadConvertorException.EXCEPTION_NAME)
                        .errorCode(BadConvertorException.ERROR_CODE)
                        .message(ex.getMessage())
                        .build());
    }

}
