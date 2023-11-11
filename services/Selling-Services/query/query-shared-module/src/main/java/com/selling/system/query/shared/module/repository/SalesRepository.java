package com.selling.system.query.shared.module.repository;

import com.selling.system.query.shared.module.entites.Sale;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

/**
 * This interface in order to provide the needed functionalities to do operations on
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
public interface SalesRepository {

    /**
     * This method is used to retrieve one or more sales documents based on the provided query.
     *
     * @param query {@link Query} contains the needed documents to be retrieved from the database.
     * @return {@link Flux}<{@link Sale}> which represents the retrieved sales based on the provided query.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<Sale> getSales(Query query);

}
