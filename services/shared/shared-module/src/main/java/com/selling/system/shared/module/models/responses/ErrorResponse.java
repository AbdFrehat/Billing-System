package com.selling.system.shared.module.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements AbstractErrorResponse {

    private String errorCode;

    private String exceptionName;

    private Object message;

    private ErrorResponse errorResponse;
}
