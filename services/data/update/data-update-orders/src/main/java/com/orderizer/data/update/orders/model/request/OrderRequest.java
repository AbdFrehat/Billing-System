package com.orderizer.data.update.orders.model.request;

import com.orderizer.data.update.orders.model.entity.Customer;
import com.orderizer.data.update.orders.model.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private long localIdentifier;

    private String storeLocation;

    private List<Item> items;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;

}
