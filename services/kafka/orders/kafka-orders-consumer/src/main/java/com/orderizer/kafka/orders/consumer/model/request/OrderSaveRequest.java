package com.orderizer.kafka.orders.consumer.model.request;

import com.orderizer.core.models.entities.Customer;
import com.orderizer.core.models.entities.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderSaveRequest {

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
