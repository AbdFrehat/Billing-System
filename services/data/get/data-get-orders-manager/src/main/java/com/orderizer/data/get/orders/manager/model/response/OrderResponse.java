package com.orderizer.data.get.orders.manager.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orderizer.data.get.orders.manager.model.common.Customer;
import com.orderizer.data.get.orders.manager.model.common.Item;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private long globalIdentifier;

    private long localIdentifier;

    private String orderDate;

    private List<Item> items;

    private String storeLocation;

    private BigDecimal totalPrice;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
