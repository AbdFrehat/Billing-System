package com.orderizer.data.get.search.orders.model.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document("orders")
public class Order {

    @Id
    private String id;

    private String globalIdentifier;

    private Long localIdentifier;

    private Date orderDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
