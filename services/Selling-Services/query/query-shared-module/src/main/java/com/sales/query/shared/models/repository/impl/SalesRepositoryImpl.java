package com.sales.query.shared.models.repository.impl;

import com.sales.query.shared.models.entites.Sale;
import com.sales.query.shared.models.repository.SalesRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class SalesRepositoryImpl implements SalesRepository {


    private final ReactiveMongoTemplate mongoTemplate;

    public SalesRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Sale> getSales(Query query) {
        return mongoTemplate.find(query, Sale.class).log();
    }
}
