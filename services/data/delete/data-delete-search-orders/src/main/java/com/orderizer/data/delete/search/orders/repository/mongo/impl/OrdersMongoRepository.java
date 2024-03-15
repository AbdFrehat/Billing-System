package com.orderizer.data.delete.search.orders.repository.mongo.impl;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.delete.search.orders.model.entity.Order;
import com.orderizer.data.delete.search.orders.model.request.OrdersDeleteRequest;
import com.orderizer.data.delete.search.orders.repository.api.OrdersRepository;
import com.orderizer.data.delete.search.orders.repository.mongo.api.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final QueryBuilder queryBuilder;

    @Override
    public Flux<DeleteResult> deleteOrders(OrdersDeleteRequest ordersDeleteRequest) {
        return queryBuilder.build(ordersDeleteRequest)
                .flatMapMany(query -> reactiveMongoTemplate.remove(query, Order.class));
    }


}
