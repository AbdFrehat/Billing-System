package com.selling.system.query.manager.sales.models.entites;

import com.selling.system.shared.module.models.entities.AbstractItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item implements AbstractItem {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
