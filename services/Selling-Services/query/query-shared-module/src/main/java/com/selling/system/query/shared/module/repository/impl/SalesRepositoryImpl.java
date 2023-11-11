package com.selling.system.query.shared.module.repository.impl;

import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.query.shared.module.repository.SalesRepository;
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
