package com.orderizer.data.save.order.repository.mongo.impl;

import com.orderizer.data.save.order.model.entity.GlobalIdentifier;
import com.orderizer.data.save.order.model.entity.LocalIdentifier;
import com.orderizer.data.save.order.model.entity.Order;
import com.orderizer.data.save.order.model.request.OrderSaveRequest;
import com.orderizer.data.save.order.repository.api.OrdersRepository;
import com.orderizer.data.save.order.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class OrdersMongoRepository implements OrdersRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Order> saveOrder(OrderSaveRequest orderSaveRequest) {
        return Mono.zip(incrementAndGetGlobalIdentifier(),
                        incrementAndGetLocalIdentifier(orderSaveRequest.getStoreLocation()),
                        OrderUtil.calculateTotalPrice(orderSaveRequest.getItems()))
                .map(tuple -> Order.builder()
                        .orderDate(LocalDateTime.now())
                        .items(orderSaveRequest.getItems())
                        .purchaseMethod(orderSaveRequest.getPurchaseMethod())
                        .globalIdentifier(tuple.getT1().getIdentifier())
                        .localIdentifier(tuple.getT2().getIdentifier())
                        .customer(orderSaveRequest.getCustomer())
                        .couponUsed(orderSaveRequest.isCouponUsed())
                        .storeLocation(orderSaveRequest.getStoreLocation())
                        .totalPrice(tuple.getT3())
                        .build())
                .flatMap(reactiveMongoTemplate::save);
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
