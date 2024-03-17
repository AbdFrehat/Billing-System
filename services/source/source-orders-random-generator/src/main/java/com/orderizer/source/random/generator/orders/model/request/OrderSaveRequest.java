package com.orderizer.source.random.generator.orders.model.request;

import com.orderizer.source.random.generator.orders.model.common.Customer;
import com.orderizer.source.random.generator.orders.model.common.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderSaveRequest {

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
