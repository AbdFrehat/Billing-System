package com.orderizer.data.delete.orders.manager.model;

import lombok.Data;

@Data
public class LocalIdentifierCriteria {
    private long localIdentifier;
    private String storeLocation;
}