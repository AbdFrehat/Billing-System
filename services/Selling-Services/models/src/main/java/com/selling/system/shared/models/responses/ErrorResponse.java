package com.selling.system.shared.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements AbstractErrorResponse {

    private int errorCode;

    private String exceptionName;

    private Object message;
}
