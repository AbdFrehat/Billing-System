package com.selling.system.data.shared.module.service;

import com.mongodb.client.result.DeleteResult;
import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.shared.module.models.commands.QueryCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * This SalesService interface acts as a service layer between the controller and the
 * repository.
 * Responsible for:
 * <ul
 * <li>Building the Query objects.</li>
 * <li>Paginate the response.</li>
 * <li>Validate the Request.</li>
 * </ul>
 *
 * @author Abd Frehat
 * @since 1.0
 */
public interface SalesService {

    /**
     * This service function responsible to retrieve the filtered sales based on the query command.
     * <ul>
     *     <lu>1. it builds the Query from the received query command.</lu>
     *     <lu>2. calls the getSales method from salesRepository.</lu>
     * </ul>
     *
     * @param queryCommand: {@link QueryCommand} contains the list of fields that the query searches on.
     * @return {@link Flux}<{@link SaleDocument}> to be sent back in the response.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<SaleDocument> getSales(QueryCommand queryCommand);

    Mono<SaleDocument> saveSale(SaleDocument sale);

    Flux<SaleDocument> saveSales(List<SaleDocument> sales);

    Mono<SaleDocument> updateSale(SaleDocument sale);

    Flux<SaleDocument> updateSales(List<SaleDocument> sale);

    Mono<DeleteResult> deleteSale(SaleDocument sale);

    Mono<DeleteResult> deleteSalesByQuery(QueryCommand queryCommand);

    Mono<DeleteResult> deleteSales(List<SaleDocument> saleDocuments);

    Mono<Long> count(QueryCommand queryCommand);
}
