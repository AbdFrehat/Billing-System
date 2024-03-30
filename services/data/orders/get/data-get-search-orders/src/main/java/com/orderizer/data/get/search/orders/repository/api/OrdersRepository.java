package com.orderizer.data.get.search.orders.repository.api;

import com.orderizer.data.get.search.orders.model.entity.Order;
import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Flux<Order> findOrders(OrdersGetRequest ordersGetRequest, int pageNumber, int pageSize);

    Mono<Long> countOrders(OrdersGetRequest ordersGetRequest);

    Mono<Order> findOrderByGlobalIdentifier(Long globalIdentifier);

    Mono<Order> findOrderByLocalIdentifier(Long localIdentifier, String storeLocation);

}
