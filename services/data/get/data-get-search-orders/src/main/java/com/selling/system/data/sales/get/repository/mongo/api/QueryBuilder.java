package com.selling.system.data.sales.get.repository.mongo.api;

import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public interface QueryBuilder {

    Mono<Query> build(OrdersGetRequest ordersGetRequest);
}
