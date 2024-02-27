package com.selling.system.reports.generate.receipt.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private String name;

    private JRBeanCollectionDataSource tags;

    private BigDecimal price;

    private int quantity;
}
