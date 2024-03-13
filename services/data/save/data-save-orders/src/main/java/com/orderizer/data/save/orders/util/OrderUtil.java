package com.orderizer.data.save.orders.util;

import com.orderizer.data.save.orders.model.entity.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtil {

    private OrderUtil() {

    }
    public static Mono<BigDecimal> calculateTotalPrice(List<Item> items) {
        return Flux.fromIterable(items).map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
