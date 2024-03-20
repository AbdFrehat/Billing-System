package com.orderizer.data.save.orders.manager.model.request;

import com.orderizer.data.save.orders.manager.model.common.Customer;
import com.orderizer.data.save.orders.manager.model.common.Item;
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
