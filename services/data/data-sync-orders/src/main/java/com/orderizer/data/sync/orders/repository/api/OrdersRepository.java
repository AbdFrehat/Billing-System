package com.orderizer.data.sync.orders.repository.api;

import com.orderizer.data.sync.orders.model.entity.Order;
import reactor.core.publisher.Flux;

public interface OrdersRepository {

    Flux<Order> fetchOrders(String storeLocation, int start, int end);
}
