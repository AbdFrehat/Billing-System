package com.selling.system.query.shared.module.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.query.shared.module.repository.SalesRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
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
     * This method is used to retrieve one or more sales documents based on the provided query.
     *
     * @param query {@link Query} contains the needed documents to be retrieved from the database.
     * @return {@link Flux}<{@link Sale}> which represents the retrieved sales based on the provided query.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Flux<Sale> getSales(Query query) {
        return mongoTemplate.find(query, Sale.class).log();
    }

    /**
     * This method is used to save  sales object in the database.
     *
     * @param sale: {@link Sale}
     * @return {@link Mono}<{@link Sale}> which contains the saved document with the assigned identity.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Mono<Sale> saveSale(Sale sale) {
        return mongoTemplate.insert(sale);
    }

    /**
     * This method is used to save list of sales objects in single batch.
     *
     * @param sales: {@link List}<{@link Sale}>
     * @return {@link Flux}<{@link Sale}> which contains the saved documents with the assigned identities.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Flux<Sale> saveSales(List<Sale> sales) {
        return mongoTemplate.insertAll(sales);
    }

    /**
     * This method is used to update the provided sale.
     *
     * @param sale {@link Sale} contains sale object which will be updated with the new object values
     * @return {@link Mono}<{@link DeleteResult}> which represents the updated sale object from the database.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Mono<Sale> updateSale(Sale sale) {
        return mongoTemplate.save(sale);
    }

    /**
     * This method is used to delete the provided sale object from the database.
     *
     * @param sale {@link Sale} contains the needed sale to be deleted from the database.
     * @return {@link Mono}<{@link DeleteResult}> which represents status of deleted documents.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Mono<DeleteResult> deleteSale(Sale sale) {
        return mongoTemplate.remove(sale);
    }


    /**
     * This method is used to delete one or more sales documents based on the provided query.
     *
     * @param query {@link Query} contains the needed documents to be deleted from the database.
     * @return {@link Mono}<{@link DeleteResult}> which represents status of deleted documents.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Mono<DeleteResult> deleteSales(Query query) {
        return mongoTemplate.remove(query, Sale.class);
    }


}
