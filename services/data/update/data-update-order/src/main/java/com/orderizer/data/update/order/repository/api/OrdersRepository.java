package com.orderizer.data.update.order.repository.api;

import com.orderizer.data.update.order.model.entity.Order;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Mono<Order> updateOrder(Order order);
    Mono<Order> findOrderByLocalIdentifier(long localIdentifier, String storeLocation);

}
