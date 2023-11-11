package com.selling.system.shared.module.handlers;

import com.selling.system.shared.module.models.exceptions.BadConvertorException;
import com.selling.system.shared.module.models.exceptions.FieldTypeNotFoundException;
import com.selling.system.shared.module.models.exceptions.PurchaseMethodNotFoundException;
import com.selling.system.shared.module.models.responses.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Objects;

import static com.selling.system.shared.module.models.constants.ExceptionsConstantCodes.*;


@ControllerAdvice
public class GlobalExceptionHandlers {

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

    @ExceptionHandler(FieldTypeNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleFieldNotFoundException(FieldTypeNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(FieldTypeNotFoundException.class.getSimpleName())
                        .errorCode(FIELD_TYPE_NOT_FOUND_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(PurchaseMethodNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseMethodNotFoundException(PurchaseMethodNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(PurchaseMethodNotFoundException.class.getSimpleName())
                        .errorCode(PURCHASE_METHOD_NOT_FOUND_EXCEPTION)
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
