package com.orderizer.data.sync.orders.repository.api;

import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import reactor.core.publisher.Flux;

public interface OrdersMongoRepository {

    Flux<MongoOrder> fetchOrders(String storeLocation, int start, int end);
}
