package com.orderizer.data.save.orders.model.request;

import com.orderizer.data.save.orders.model.entity.Customer;
import com.orderizer.data.save.orders.model.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
