package com.selling.system.shared.module.handlers;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.selling.system.shared.module.exceptions.BadConvertorException;
import com.selling.system.shared.module.exceptions.PayloadBadFormatException;
import com.selling.system.shared.module.exceptions.business.FieldTypeNotFoundException;
import com.selling.system.shared.module.exceptions.business.PurchaseMethodNotFoundException;
import com.selling.system.shared.module.exceptions.business.CommandTypeNotSupportedException;
import com.selling.system.shared.module.exceptions.general.ClientException;
import com.selling.system.shared.module.models.constants.ExceptionsConstantCodes;
import com.selling.system.shared.module.models.responses.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandlers {


    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleServiceNotAvailableException(ClientException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ErrorResponse.builder()
                        .exceptionName(ClientException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.CLIENT_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(BadConvertorException.class)
    public ResponseEntity<ErrorResponse> handleBadConvertorException(BadConvertorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(BadConvertorException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.BAD_CONVERTOR_EXCEPTION_ERROR_CODE)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(FieldTypeNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleFieldNotFoundException(FieldTypeNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(FieldTypeNotFoundException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.FIELD_TYPE_NOT_FOUND_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(PurchaseMethodNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseMethodNotFoundException(PurchaseMethodNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(PurchaseMethodNotFoundException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.PURCHASE_METHOD_NOT_FOUND_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(PayloadBadFormatException.class)
    public ResponseEntity<ErrorResponse> handlePayloadBadFormatException(PayloadBadFormatException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(PayloadBadFormatException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.PAYLOAD_BAD_FORMAT_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(CommandTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleQueryMethodNotFoundException(CommandTypeNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(CommandTypeNotSupportedException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.QUERY_METHOD_NOT_FOUND_EXCEPTION)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> handleQueryMethodNotFoundException(MismatchedInputException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(MismatchedInputException.class.getSimpleName())
                        .errorCode(ExceptionsConstantCodes.MISMATCHED_INPUT_EXCEPTION)
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
                        .errorCode(ExceptionsConstantCodes.WEB_EXCHANGE_BIND_EXCEPTION_ERROR_CODE)
                        .message(errorMessages)
                        .build());
    }

}
