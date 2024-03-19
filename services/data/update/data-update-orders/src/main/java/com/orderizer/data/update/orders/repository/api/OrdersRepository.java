package com.orderizer.data.update.orders.repository.api;


import com.orderizer.data.update.orders.model.entity.Order;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Mono<Order> updateOrder(Order order);

    Mono<Order> findOrderByLocalIdentifier(long localIdentifier, String storeLocation);
}
