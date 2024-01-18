package com.selling.system.shared.module.handlers;

import com.selling.system.shared.module.exceptions.general.BusinessException;
import com.selling.system.shared.module.exceptions.general.ClientException;
import com.selling.system.shared.module.exceptions.general.TechnicalException;
import com.selling.system.shared.module.models.responses.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static com.selling.system.shared.module.utils.ResourceBundlesUtil.getExceptionCode;
import static com.selling.system.shared.module.utils.ResourceBundlesUtil.getExceptionMessage;


@ControllerAdvice
public class GlobalExceptionHandlers {

    private final MessageSource messageSource;

    private final MessageSource commonMessageSource;

    public GlobalExceptionHandlers(MessageSource messageSource, MessageSource commonMessageSource) {
        this.messageSource = messageSource;
        this.commonMessageSource = commonMessageSource;
    }

    @ExceptionHandler(Exception.class)
    public <T extends Exception> ResponseEntity<ErrorResponse> handleGenericException(T t) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8))
                        .errorCode(getExceptionCode(messageSource, t))
                        .build());
    }

    @ExceptionHandler(ClientException.class)
    public <T extends ClientException> ResponseEntity<ErrorResponse> handleServiceNotAvailableException(T t) {
        return ResponseEntity
                .status(t.getStatusCode())
                .body(ErrorResponse.builder()
                        .exceptionName(ClientException.class.getSimpleName())
                        .errorCode(getExceptionCode(commonMessageSource, t))
                        .message(getExceptionMessage(commonMessageSource, t, StandardCharsets.UTF_8))
                        .build());
    }

    @ExceptionHandler(BusinessException.class)
    public <T extends BusinessException> ResponseEntity<ErrorResponse> handleBusinessException(T t) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8))
                        .errorCode(getExceptionCode(messageSource, t))
                        .build());
    }

    @ExceptionHandler(TechnicalException.class)
    public <T extends TechnicalException> ResponseEntity<ErrorResponse> handleClientException(T t) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8))
                        .errorCode(getExceptionCode(messageSource, t))
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
                        .errorCode(getExceptionCode(commonMessageSource, ex))
                        .message(getExceptionMessage(commonMessageSource, ex, StandardCharsets.UTF_8, String.join("-", errorMessages)))
                        .build());
    }

}
