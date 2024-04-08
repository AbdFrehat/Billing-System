package com.orderizer.core.models.responses;

public interface AbstractErrorResponse {

    String getErrorCode();

    String getExceptionName();

    Object getMessage();

}
