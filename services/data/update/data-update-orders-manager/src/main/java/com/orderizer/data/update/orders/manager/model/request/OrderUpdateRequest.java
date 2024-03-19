package com.orderizer.data.update.orders.manager.model.request;

import com.orderizer.data.update.orders.manager.model.common.Customer;
import com.orderizer.data.update.orders.manager.model.common.Item;
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
