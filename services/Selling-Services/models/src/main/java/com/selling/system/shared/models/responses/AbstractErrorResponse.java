package com.selling.system.shared.models.responses;

public interface AbstractErrorResponse {

    int getErrorCode();

    String getExceptionName();

    Object getMessage();

}
