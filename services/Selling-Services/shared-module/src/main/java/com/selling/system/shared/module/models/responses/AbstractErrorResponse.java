package com.selling.system.shared.module.models.responses;

public interface AbstractErrorResponse {

    int getErrorCode();

    String getExceptionName();

    Object getMessage();

}
