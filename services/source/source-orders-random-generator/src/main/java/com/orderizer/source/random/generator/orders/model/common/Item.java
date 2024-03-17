package com.orderizer.source.random.generator.orders.model.common;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Item {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
