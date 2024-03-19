package com.orderizer.data.update.order.repository.mongo;

import com.orderizer.data.update.order.exception.OrderNotFoundException;
import com.orderizer.data.update.order.model.entity.Order;
import com.orderizer.data.update.order.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Order> updateOrder(Order order) {
        return reactiveMongoTemplate.save(order);
    }

    @Override
    public Mono<Order> findOrderByLocalIdentifier(long localIdentifier, String storeLocation) {
        return reactiveMongoTemplate.find(Query.query(where("localIdentifier").is(localIdentifier).and("storeLocation").is(storeLocation)), Order.class)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(OrderNotFoundException::new));
    }
}
