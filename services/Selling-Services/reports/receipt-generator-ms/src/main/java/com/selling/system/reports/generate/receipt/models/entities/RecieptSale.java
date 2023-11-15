package com.selling.system.reports.generate.receipt.models.entities;


import com.selling.system.shared.module.models.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecieptSale extends Sale {

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private int totalQuantity = 0;
}
