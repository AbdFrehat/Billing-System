package com.selling.system.shared.module.models.commands;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportDataCommand implements ExportDataCommandAbstract {

    private String fromDate;

    private String toDate;

    private String storeLocation;

    private String purchaseMethod;

    private int satisfaction;

    private Boolean couponUsed;

}
