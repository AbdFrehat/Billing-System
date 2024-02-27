package com.selling.system.data.shared.module.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.data.shared.module.repository.SalesRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * This class implements {@link SalesRepository} interface in order to provide the needed functionalities to do operations on
 * The sales collection.
 * <ul>
 *     <li>Retrieve one or more documents</li>
 *     <li>Create new documents</li>
 *     <li>Update existing documents</li>
 *     <li>Delete one or more documents</li>
 * </ul>
 *
 * @author Abd Frehat
 * @since 1.0
 */
@Repository
public class SalesRepositoryImpl implements SalesRepository {


    private final ReactiveMongoTemplate mongoTemplate;

    public SalesRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<SaleDocument> getSales(Query query) {
        return mongoTemplate.find(query, SaleDocument.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Mono<SaleDocument> saveSale(SaleDocument sale) {
        return mongoTemplate.insert(sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<SaleDocument> saveSales(List<SaleDocument> sales) {
        return mongoTemplate.insertAll(sales);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<SaleDocument> updateSale(SaleDocument sale) {
        return mongoTemplate.save(sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<SaleDocument> updateSales(List<SaleDocument> sales) {
        return Flux.concat(sales.stream().map(this.mongoTemplate::save).toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<DeleteResult> deleteSale(SaleDocument sale) {
        return mongoTemplate.remove(sale);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<DeleteResult> deleteSalesByQuery(Query query) {
        return mongoTemplate.remove(query, SaleDocument.class);
    }

    @Override
    public Mono<DeleteResult> deleteSales(List<SaleDocument> saleDocuments) {
        return Flux.fromIterable(saleDocuments)
                .flatMap(mongoTemplate::remove)
                .map(DeleteResult::getDeletedCount)
                .reduce(0L, Long::sum)
                .flatMap(deletedCount -> Mono.just(DeleteResult.acknowledged(deletedCount)));
    }


    @Override
    public Mono<Long> count(Query query) {
        return mongoTemplate.count(query, SaleDocument.class);
    }
}
