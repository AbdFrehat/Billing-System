package com.orderizer.data.delete.order.repository.impl.mongo;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.delete.order.model.entity.Order;
import com.orderizer.data.delete.order.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;


    @Override
    public Mono<DeleteResult> deleteOrderByGlobalIdentifier(long globalIdentifier) {
        return reactiveMongoTemplate.remove(Query.query(where("globalIdentifier").is(globalIdentifier)), Order.class);
    }

    @Override
    public Mono<DeleteResult> deleteOrderByLocalIdentifier(long localIdentifier, String storeLocation) {
        return reactiveMongoTemplate.remove(Query.query(where("localIdentifier").is(localIdentifier)
                .and("storeLocation").is(storeLocation)), Order.class);
    }
}
