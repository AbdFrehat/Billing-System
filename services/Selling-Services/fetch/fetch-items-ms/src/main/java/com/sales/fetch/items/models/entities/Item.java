package com.sales.fetch.items.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
