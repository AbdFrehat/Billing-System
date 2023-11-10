package com.sales.query.shared.models.repository.impl;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.entites.Sale;
import com.sales.query.shared.models.repository.SalesRepository;
import com.sales.query.shared.models.service.QueryBuilderService;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SalesRepositoryImpl implements SalesRepository {

    private final QueryBuilderService queryBuilderService;

    private final ReactiveMongoTemplate mongoTemplate;

    public SalesRepositoryImpl(QueryBuilderService queryBuilderService, ReactiveMongoTemplate mongoTemplate) {
        this.queryBuilderService = queryBuilderService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Sale> getSales(QueryCommand queryCommand) {
        Query query = queryBuilderService.buildQuery(queryCommand);
        return mongoTemplate.find(query, Sale.class).log();
    }
}
