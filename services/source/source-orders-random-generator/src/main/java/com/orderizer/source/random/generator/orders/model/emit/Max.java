package com.orderizer.source.random.generator.orders.model.emit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Max {
    private int items;
    private int tags;
}