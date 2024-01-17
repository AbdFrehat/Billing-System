package com.selling.system.shared.module.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements AbstractErrorResponse {

    private String errorCode;

    private String exceptionName;

    private Object message;
}
