package com.orderizer.data.delete.search.orders.repository.mongo.api;

import com.orderizer.data.delete.search.orders.model.request.DeleteOrdersRequest;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public interface QueryBuilder {

    Mono<Query> build(DeleteOrdersRequest deleteOrdersRequest);
}
