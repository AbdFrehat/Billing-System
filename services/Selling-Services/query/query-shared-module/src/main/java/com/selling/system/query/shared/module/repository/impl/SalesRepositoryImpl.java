package com.selling.system.query.shared.module.repository.impl;

import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.query.shared.module.repository.SalesRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

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
}
