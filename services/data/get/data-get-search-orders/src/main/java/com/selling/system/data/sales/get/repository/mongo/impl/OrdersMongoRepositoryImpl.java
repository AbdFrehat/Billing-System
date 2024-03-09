package com.selling.system.data.sales.get.repository.mongo.impl;

import com.selling.system.data.sales.get.model.entity.Order;
import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
import com.selling.system.data.sales.get.model.response.OrdersCountResponse;
import com.selling.system.data.sales.get.repository.api.OrdersRepository;
import com.selling.system.data.sales.get.repository.mongo.api.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepositoryImpl implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final QueryBuilder queryBuilder;

    @Override
    public Flux<Order> findOrders(OrdersGetRequest ordersGetRequest) {
        return queryBuilder.build(ordersGetRequest)
                .flatMapMany(query -> reactiveMongoTemplate.find(query, Order.class));
    }

    @Override
    public Mono<Long> countOrders(OrdersGetRequest ordersGetRequest) {
        return queryBuilder.build(ordersGetRequest)
                .flatMap(query -> reactiveMongoTemplate.count(query, Order.class));
    }

    @Override
    public Mono<Order> findOrderByGlobalIdentifier(String globalIdentifier) {
        return null;
    }

    @Override
    public Mono<Order> findOrderByLocalIdentifier(String localIdentifier, String storeLocation) {
        return null;
    }
}
