package com.selling.system.export.data.xlsx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {

    private String fromDate;

    private String toDate;

    private String storeLocation;

    private String purchaseMethod;

    private int satisfaction;

    private Boolean couponUsed;

    private long total;

    private JRBeanCollectionDataSource salesDS;
}
