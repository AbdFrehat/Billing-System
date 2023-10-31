package com.sale.persistence.kafka.sale.producer.entities;

import com.selling.shared.models.entities.AbstractSale;
import com.selling.shared.models.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
