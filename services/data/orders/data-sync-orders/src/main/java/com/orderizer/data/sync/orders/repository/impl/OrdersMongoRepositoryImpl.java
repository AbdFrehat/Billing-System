package com.orderizer.data.sync.orders.repository.impl;

import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import com.orderizer.data.sync.orders.repository.api.OrdersMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepositoryImpl implements OrdersMongoRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<MongoOrder> fetchOrders(String storeLocation, int start, int end) {
        return mongoTemplate.find(query(where("storeLocation").is(storeLocation)
                        .and("localIdentifier").gte(start).lte(end))
                        .with(Sort.by(Sort.Order.asc("localIdentifier"))),
                MongoOrder.class);
    }
}
