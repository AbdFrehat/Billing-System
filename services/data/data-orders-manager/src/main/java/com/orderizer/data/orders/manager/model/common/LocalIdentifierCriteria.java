package com.orderizer.data.orders.manager.model.common;

import lombok.Data;

@Data
public class LocalIdentifierCriteria {
    private long localIdentifier;
    private String storeLocation;
}