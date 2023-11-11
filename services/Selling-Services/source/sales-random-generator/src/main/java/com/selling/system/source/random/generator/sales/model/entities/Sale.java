package com.selling.system.source.random.generator.sales.model.entities;

import com.selling.system.shared.module.models.entities.AbstractSale;
import com.selling.system.shared.module.models.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale implements AbstractSale {

    private String id;

    private Date saleDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private PurchaseMethod purchaseMethod;
}
