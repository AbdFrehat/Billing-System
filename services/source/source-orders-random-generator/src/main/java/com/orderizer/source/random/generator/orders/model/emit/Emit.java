package com.orderizer.source.random.generator.orders.model.emit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Emit {
    private Long duration;
    private Max max;


}