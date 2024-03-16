package com.orderizer.data.update.order.model.response;

import com.orderizer.data.update.order.model.entity.Customer;
import com.orderizer.data.update.order.model.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private String globalIdentifier;

    private Long localIdentifier;

    private LocalDateTime orderDate;

    private List<Item> items;

    private String storeLocation;

    private BigDecimal totalPrice;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
