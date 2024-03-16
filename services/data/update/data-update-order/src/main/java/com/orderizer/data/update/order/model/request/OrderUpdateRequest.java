package com.orderizer.data.update.order.model.request;

import com.orderizer.data.update.order.model.entity.Customer;
import com.orderizer.data.update.order.model.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateRequest {

    private List<Item> items;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
