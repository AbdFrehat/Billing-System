package com.selling.system.export.data.pdf.model.dto;

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
public class ItemsDTO {

    private JRBeanCollectionDataSource itemsDS;

    private BigDecimal totalPrice;

    private int totalQuantity;

}
