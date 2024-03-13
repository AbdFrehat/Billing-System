package com.orderizer.data.save.orders.model.response;

import com.orderizer.data.save.orders.model.entity.Customer;
import com.orderizer.data.save.orders.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long globalIdentifier;

    private long localIdentifier;

    private LocalDateTime orderDate;

    private List<Item> items;

    private BigDecimal totalPrice;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
