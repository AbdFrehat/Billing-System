package com.orderizer.core.models.commands;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportDataFilter implements ExportDataFilterAbstract {

    @NotNull(message = "from date must not be null")
    private String fromDate;

    @NotNull(message = "to date must not be null")
    private String toDate;

    private String storeLocation;

    private String purchaseMethod;

    private int satisfaction;

    private Boolean couponUsed;

}
