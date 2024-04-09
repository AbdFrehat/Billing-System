package com.orderizer.export.data.json.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExportOrdersFilterRequest {

    @NotNull(message = "from date must not be null")
    private LocalDateTime fromDate;

    @NotNull(message = "to date must not be null")
    private LocalDateTime toDate;

    private String storeLocation;

    private String purchaseMethod;

    private Integer satisfaction;

    private Boolean couponUsed;

}
