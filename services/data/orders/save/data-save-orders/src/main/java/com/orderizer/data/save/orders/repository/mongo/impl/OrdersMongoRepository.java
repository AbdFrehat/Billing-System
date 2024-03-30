package com.orderizer.data.save.orders.repository.mongo.impl;

import com.orderizer.data.save.orders.model.entity.GlobalIdentifier;
import com.orderizer.data.save.orders.model.entity.LocalIdentifier;
import com.orderizer.data.save.orders.model.entity.Order;
import com.orderizer.data.save.orders.model.request.OrdersSaveRequest;
import com.orderizer.data.save.orders.repository.api.OrdersRepository;
import com.orderizer.data.save.orders.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Order> saveOrders(OrdersSaveRequest ordersSaveRequest) {
        return Flux.fromIterable(ordersSaveRequest.getOrders())
                .parallel()
                .flatMap(order -> Mono.zip(incrementAndGetGlobalIdentifier(),
                        incrementAndGetLocalIdentifier(order.getStoreLocation()),
                        OrderUtil.calculateTotalPrice(order.getItems()))
                .map(tuple -> Order.builder()
                        .orderDate(LocalDateTime.now())
                        .items(order.getItems())
                        .purchaseMethod(order.getPurchaseMethod())
                        .globalIdentifier(tuple.getT1().getIdentifier())
                        .localIdentifier(tuple.getT2().getIdentifier())
                        .customer(order.getCustomer())
                        .couponUsed(order.isCouponUsed())
                        .storeLocation(order.getStoreLocation())
                        .totalPrice(tuple.getT3())
                        .build())
                .flatMap(reactiveMongoTemplate::save))
                .sequential();
    }

    private Mono<LocalIdentifier> incrementAndGetLocalIdentifier(String storeLocation) {
        return reactiveMongoTemplate.findAndModify(query(where("storeLocation").is(storeLocation)),
                new Update().inc("identifier", 1),
                new FindAndModifyOptions().upsert(true).returnNew(true),
                LocalIdentifier.class);
    }

    private Mono<GlobalIdentifier> incrementAndGetGlobalIdentifier() {
        return reactiveMongoTemplate.findAndModify(query(Criteria.where("identifier").exists(true)),
                new Update().inc("identifier", 1),
                new FindAndModifyOptions().upsert(true).returnNew(true),
                GlobalIdentifier.class);
    }
}
