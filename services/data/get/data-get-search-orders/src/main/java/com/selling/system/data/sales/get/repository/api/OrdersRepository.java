package com.selling.system.data.sales.get.repository.api;

import com.selling.system.data.sales.get.model.entity.Order;
import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Flux<Order> findOrders(OrdersGetRequest ordersGetRequest);

    Mono<Long> countOrders(OrdersGetRequest ordersGetRequest);

    Mono<Order> findOrderByGlobalIdentifier(String globalIdentifier);

    Mono<Order> findOrderByLocalIdentifier(String localIdentifier, String storeLocation);

}
