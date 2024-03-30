package com.orderizer.data.get.search.orders.repository.mongo.api;

import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public interface QueryBuilder {

    Mono<Query> build(OrdersGetRequest ordersGetRequest);
}
