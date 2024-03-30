package com.orderizer.data.sync.orders.model.entity.mongo;

import com.orderizer.data.sync.orders.model.common.Customer;
import com.orderizer.data.sync.orders.model.common.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MongoOrder {

    @Id
    private String id;

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
