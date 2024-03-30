package com.orderizer.data.delete.search.orders.model.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("orders")
public class Order {

    @Id
    private String id;

    private String globalIdentifier;

    private Long localIdentifier;

    private LocalDateTime orderDate;

    private List<Item> items;

    private BigDecimal totalPrice;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
