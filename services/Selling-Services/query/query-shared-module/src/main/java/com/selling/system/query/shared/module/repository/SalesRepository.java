package com.selling.system.query.shared.module.repository;

import com.mongodb.client.result.DeleteResult;
import com.selling.system.query.shared.module.entites.SaleDocument;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
     * @return {@link Flux}<{@link SaleDocument}> which represents the retrieved sales based on the provided query.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<SaleDocument> getSales(Query query);

    /**
     * This method is used to save  sales object in the database.
     *
     * @param sale: {@link SaleDocument}
     * @return {@link Mono}<{@link SaleDocument}> which contains the saved document with the assigned identity.
     * @author Abd Frehat
     * @since 1.0
     */
    Mono<SaleDocument> saveSale(SaleDocument sale);

    /**
     * This method is used to save list of sales objects in single batch.
     *
     * @param sales: {@link List}<{@link SaleDocument}>
     * @return {@link Flux}<{@link SaleDocument}> which contains the saved documents with the assigned identities.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<SaleDocument> saveSales(List<SaleDocument> sales);

    /**
     * This method is used to update the provided sale.
     *
     * @param sale {@link SaleDocument} contains sale object which will be updated with the new object values
     * @return {@link Mono}<{@link DeleteResult}> which represents the updated sale object from the database.
     * @author Abd Frehat
     * @since 1.0
     */
    Mono<SaleDocument> updateSale(SaleDocument sale);

    /**
     * This method is used to update the provided sale.
     *
     * @param  sales: {@link List}<{@link SaleDocument}> contains list of sale objects which will be updated with the new objects values
     * @return {@link Flux}<{@link SaleDocument}> which represents the updated sales objects from the database.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<SaleDocument> updateSales(List<SaleDocument> sales);

    /**
     * This method is used to delete the provided sale object from the database.
     *
     * @param sale {@link SaleDocument} contains the needed sale to be deleted from the database.
     * @return {@link Mono}<{@link DeleteResult}> which represents status of deleted documents.
     * @author Abd Frehat
     * @since 1.0
     */
    Mono<DeleteResult> deleteSale(SaleDocument sale);

    /**
     * This method is used to delete one or more sales documents based on the provided query.
     *
     * @param query {@link Query} contains the needed documents to be deleted from the database.
     * @return {@link Mono}<{@link DeleteResult}> which represents status of deleted documents.
     * @author Abd Frehat
     * @since 1.0
     */
    Mono<DeleteResult> deleteSales(Query query);

}
