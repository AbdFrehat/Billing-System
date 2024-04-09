package com.orderizer.data.sync.orders.repository.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import com.orderizer.data.sync.orders.repository.api.OrdersElasticRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class OrdersElasticRepositoryImpl implements OrdersElasticRepository {

    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

    @Override
    public Flux<ElasticOrder> fetchOrders(String storeLocation, int start, int end) {
        int pageSize = end - start + 1;
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Query query = CriteriaQuery.builder(
                        Criteria.where("storeLocation").is(storeLocation)
                                .and(Criteria.where("localIdentifier").greaterThanEqual(start).lessThanEqual(end))
                ).withPageable(pageRequest)
                .build();
        return reactiveElasticsearchOperations.search(query, ElasticOrder.class)
                .map(SearchHit::getContent);
    }

    @Override
    public Flux<ElasticOrder> updateOrders(List<ElasticOrder> elasticOrders) {
        return reactiveElasticsearchOperations.saveAll(elasticOrders, ElasticOrder.class);
    }

    @Override
    public Flux<String> deleteOrders(List<ElasticOrder> elasticOrders) {
        return Flux.fromIterable(elasticOrders).flatMap(reactiveElasticsearchOperations::delete);
    }
}
