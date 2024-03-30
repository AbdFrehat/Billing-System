package com.orderizer.data.delete.orders.repository.impl.mongo;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.delete.orders.model.entity.Order;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByLocalIdentifiersRequest;
import com.orderizer.data.delete.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<DeleteResult> deleteOrdersByGlobalIdentifier(DeleteOrdersByGlobalIdentifiersRequest request) {
        return Flux.fromIterable(request.getGlobalIdentifiers())
                .parallel()
                .flatMap(globalIdentifier -> reactiveMongoTemplate.remove(Query.query(where("globalIdentifier").is(globalIdentifier)), Order.class))
                .sequential();
    }

    @Override
    public Flux<DeleteResult> deleteOrdersByLocalIdentifier(DeleteOrdersByLocalIdentifiersRequest request) {
        return Flux.fromIterable(request.getLocalIdentifierCriteria())
                .parallel()
                .flatMap(localIdentifierCriteria -> reactiveMongoTemplate.remove(Query.query(where("localIdentifier").is(localIdentifierCriteria.getLocalIdentifier())
                        .and("storeLocation").is(localIdentifierCriteria.getStoreLocation())), Order.class))
                .sequential();
    }
}
