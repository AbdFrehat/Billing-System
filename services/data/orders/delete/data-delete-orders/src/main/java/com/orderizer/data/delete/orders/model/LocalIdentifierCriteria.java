package com.orderizer.data.delete.orders.model;

import lombok.Data;

@Data
public class LocalIdentifierCriteria {
    private Long localIdentifier;
    private String storeLocation;
}