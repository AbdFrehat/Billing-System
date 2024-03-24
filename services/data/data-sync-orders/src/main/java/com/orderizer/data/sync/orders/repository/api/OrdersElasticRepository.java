package com.orderizer.data.sync.orders.repository.api;

import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrdersElasticRepository {

    Flux<ElasticOrder> fetchOrders(String storeLocation, int start, int end);

    Flux<ElasticOrder> updateOrders(List<ElasticOrder> elasticOrders);

    Flux<String> deleteOrders(List<ElasticOrder> elasticOrders);
}
