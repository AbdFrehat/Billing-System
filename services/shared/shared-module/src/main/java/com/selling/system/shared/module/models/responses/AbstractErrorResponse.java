package com.selling.system.shared.module.models.responses;

public interface AbstractErrorResponse {

    String getErrorCode();

    String getExceptionName();

    Object getMessage();

}
