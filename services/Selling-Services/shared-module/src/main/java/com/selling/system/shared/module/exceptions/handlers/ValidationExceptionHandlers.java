package com.selling.system.shared.module.exceptions.handlers;

import com.selling.system.shared.models.exceptions.BadConvertorException;
import com.selling.system.shared.module.modles.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Objects;

import static com.selling.system.shared.models.constants.ExceptionsConstantCodes.BAD_CONVERTOR_EXCEPTION_ERROR_CODE;
import static com.selling.system.shared.models.constants.ExceptionsConstantCodes.WEB_EXCHANGE_BIND_EXCEPTION_ERROR_CODE;


@ControllerAdvice
public class ValidationExceptionHandlers {


    @ExceptionHandler(BadConvertorException.class)
    public ResponseEntity<ErrorResponse> handleBadConvertorException(BadConvertorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(BadConvertorException.class.getSimpleName())
                        .errorCode(BAD_CONVERTOR_EXCEPTION_ERROR_CODE)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(WebExchangeBindException.class.getSimpleName())
                        .errorCode(WEB_EXCHANGE_BIND_EXCEPTION_ERROR_CODE)
                        .message(errorMessages)
                        .build());
    }

}
