package com.selling.system.reports.generate.receipt.models.entities;

import com.selling.system.shared.module.models.entities.AbstractSale;
import com.selling.system.shared.module.models.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements AbstractSale {

    private String id;

    private Date saleDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private PurchaseMethod purchaseMethod;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private int totalQuantity = 0;
}
