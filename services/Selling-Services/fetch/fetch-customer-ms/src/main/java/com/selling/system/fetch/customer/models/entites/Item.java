package com.selling.system.fetch.customer.models.entites;

import com.selling.system.shared.models.entities.AbstractItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements AbstractItem {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
