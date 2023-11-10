package com.sales.query.shared.models.service;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.entites.Sale;
import reactor.core.publisher.Flux;

/**
 * This SalesService interface acts as a service layer between the controller and the
 * repository.
 * Responsible for:
 * <ul
 *  <li>Building the Query objects.</li>
 *  <li>Paginate the response.</li>
 *  <li>Validate the Request.</li>
 * </ul>
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
     * @return {@code Flux<Sale>} to be sent back in the response.
     * @author Abd Frehat
     * @since 1.0
     */
    Flux<Sale> getSales(QueryCommand queryCommand);
}
