package com.orderizer.kafka.orders.consumer.model.entity;

import com.orderizer.kafka.orders.consumer.model.common.Item;
import com.orderizer.kafka.orders.consumer.model.common.Customer;
import lombok.Data;

import java.util.List;

@Data
public class DataUpdateOrderEntity {

    private long localIdentifier;

    private String storeLocation;

    private List<Item> items;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
