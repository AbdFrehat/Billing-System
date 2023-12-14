package com.selling.system.export.data.xlsx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {

    private Date saleDate;

    private String storeLocation;

    private boolean couponUsed;

    private String purchaseMethod;

    private JRDataSource customerDS;

    private Map<String, Object> customerParameters;

    private JasperReport customerReport;

    private JRDataSource itemsDS;

    private Map<String, Object> itemsParameters;

    private JasperReport itemsReport;
}
