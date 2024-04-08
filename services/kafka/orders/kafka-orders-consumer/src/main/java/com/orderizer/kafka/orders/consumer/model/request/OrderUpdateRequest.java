package com.orderizer.kafka.orders.consumer.model.request;

import com.orderizer.kafka.orders.consumer.model.common.Customer;
import com.orderizer.kafka.orders.consumer.model.common.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateRequest {

    private long localIdentifier;

    private String storeLocation;

    private List<Item> items;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
