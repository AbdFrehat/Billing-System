package com.selling.system.data.sales.get.model.response;

import com.selling.system.data.sales.get.model.entity.Customer;
import com.selling.system.data.sales.get.model.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private String globalIdentifier;

    private Long localIdentifier;

    private Date orderDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
