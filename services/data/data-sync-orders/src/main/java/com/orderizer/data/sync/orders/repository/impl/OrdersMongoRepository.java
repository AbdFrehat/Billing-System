package com.orderizer.data.sync.orders.repository.impl;

import com.orderizer.data.sync.orders.model.entity.Order;
import com.orderizer.data.sync.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Order> fetchOrders(String storeLocation, int start, int end) {
        return mongoTemplate.find(query(where("storeLocation").is(storeLocation)
                        .and("localIdentifier").gte(start).lte(end)),
                Order.class);
    }
}
