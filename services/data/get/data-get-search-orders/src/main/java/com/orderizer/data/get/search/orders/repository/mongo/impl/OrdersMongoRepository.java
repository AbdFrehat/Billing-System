package com.orderizer.data.get.search.orders.repository.mongo.impl;

import com.orderizer.data.get.search.orders.exception.OrderNotFoundException;
import com.orderizer.data.get.search.orders.model.entity.Order;
import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
import com.orderizer.data.get.search.orders.repository.api.OrdersRepository;
import com.orderizer.data.get.search.orders.repository.mongo.api.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

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
    public Mono<Order> findOrderByGlobalIdentifier(Long globalIdentifier) {
        return reactiveMongoTemplate.find(query(where("globalIdentifier")
                        .is(globalIdentifier)), Order.class)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(OrderNotFoundException::new));
    }

    @Override
    public Mono<Order> findOrderByLocalIdentifier(Long localIdentifier, String storeLocation) {
        return reactiveMongoTemplate.find(query(where("localIdentifier")
                        .is(localIdentifier)
                        .and("storeLocation")
                        .is(storeLocation)), Order.class)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(OrderNotFoundException::new));
    }
}
