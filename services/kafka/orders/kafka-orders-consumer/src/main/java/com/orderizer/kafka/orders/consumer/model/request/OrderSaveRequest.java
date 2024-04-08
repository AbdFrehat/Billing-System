package com.orderizer.kafka.orders.consumer.model.request;

import com.selling.system.shared.module.models.entities.Customer;
import com.selling.system.shared.module.models.entities.Item;
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
