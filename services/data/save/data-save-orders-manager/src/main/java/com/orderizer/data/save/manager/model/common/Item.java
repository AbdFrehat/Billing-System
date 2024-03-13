package com.orderizer.data.save.manager.model.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Item {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
