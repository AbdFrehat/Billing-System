package com.selling.system.kafka.sales.consumer.entities;

import com.selling.system.shared.module.models.entities.AbstractSale;
import com.selling.system.shared.module.models.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements AbstractSale, Serializable {

    @Id
    private String id;

    private Date saleDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;
    
    private PurchaseMethod purchaseMethod;
}
