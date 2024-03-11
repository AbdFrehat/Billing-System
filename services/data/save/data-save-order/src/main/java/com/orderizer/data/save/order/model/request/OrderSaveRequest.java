package com.orderizer.data.save.order.model.request;

import com.orderizer.data.save.order.model.entity.Customer;
import com.orderizer.data.save.order.model.entity.Item;
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
