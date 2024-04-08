package com.orderizer.core.handlers;

import com.orderizer.core.models.responses.ErrorResponse;
import com.orderizer.core.config.CommonHeaders;
import com.orderizer.core.exceptions.general.BusinessException;
import com.orderizer.core.exceptions.general.ClientException;
import com.orderizer.core.exceptions.general.TechnicalException;
import org.springframework.beans.factory.annotation.Qualifier;
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

import static com.orderizer.core.utils.ResourceBundlesUtil.getExceptionCode;
import static com.orderizer.core.utils.ResourceBundlesUtil.getExceptionMessage;


@ControllerAdvice
public class GlobalExceptionHandlers {

    private final MessageSource messageSource;

    private final MessageSource commonMessageSource;

    private final CommonHeaders commonHeaders;

    public GlobalExceptionHandlers(@Qualifier("messageSource") MessageSource messageSource,
                                   @Qualifier("commonMessageSource") MessageSource commonMessageSource,
                                   CommonHeaders commonHeaders) {
        this.messageSource = messageSource;
        this.commonMessageSource = commonMessageSource;
        this.commonHeaders = commonHeaders;
    }

    @ExceptionHandler(Exception.class)
    public <T extends Exception> ResponseEntity<ErrorResponse> handleGenericException(T t) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8, commonHeaders.getLang()))
                        .errorCode(getExceptionCode(messageSource, t, commonHeaders.getLang()))
                        .build());
    }

    @ExceptionHandler(ClientException.class)
    public <T extends ClientException> ResponseEntity<ErrorResponse> handleServiceNotAvailableException(T t) {
        return ResponseEntity
                .status(t.getStatusCode())
                .body(ErrorResponse.builder()
                        .exceptionName(ClientException.class.getSimpleName())
                        .errorCode(getExceptionCode(commonMessageSource, t, commonHeaders.getLang()))
                        .message(getExceptionMessage(commonMessageSource, t, StandardCharsets.UTF_8, commonHeaders.getLang()))
                        .errorResponse(t.getErrorResponse())
                        .build());
    }

    @ExceptionHandler(BusinessException.class)
    public <T extends BusinessException> ResponseEntity<ErrorResponse> handleBusinessException(T t) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8, commonHeaders.getLang()))
                        .errorCode(getExceptionCode(messageSource, t, commonHeaders.getLang()))
                        .build());
    }

    @ExceptionHandler(TechnicalException.class)
    public <T extends TechnicalException> ResponseEntity<ErrorResponse> handleClientException(T t) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .exceptionName(t.getClass().getSimpleName())
                        .message(getExceptionMessage(messageSource, t, StandardCharsets.UTF_8, commonHeaders.getLang()))
                        .errorCode(getExceptionCode(messageSource, t, commonHeaders.getLang()))
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
                        .errorCode(getExceptionCode(commonMessageSource, ex, commonHeaders.getLang()))
                        .message(getExceptionMessage(commonMessageSource, ex, StandardCharsets.UTF_8, String.join("-", errorMessages)))
                        .build());
    }

}
