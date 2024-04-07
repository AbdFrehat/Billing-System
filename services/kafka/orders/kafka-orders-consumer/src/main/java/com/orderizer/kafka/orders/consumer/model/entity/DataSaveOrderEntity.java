package com.orderizer.kafka.orders.consumer.model.entity;

import com.orderizer.kafka.orders.consumer.model.common.Customer;
import com.orderizer.kafka.orders.consumer.model.common.Item;
import lombok.Data;

import java.util.List;

@Data
public class DataSaveOrderEntity {

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
