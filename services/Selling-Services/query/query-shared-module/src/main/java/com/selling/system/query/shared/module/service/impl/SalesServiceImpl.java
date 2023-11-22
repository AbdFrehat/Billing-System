package com.selling.system.query.shared.module.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.selling.system.query.shared.module.entites.SaleDocument;
import com.selling.system.query.shared.module.repository.SalesRepository;
import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.query.shared.module.service.SalesService;
import com.selling.system.shared.module.models.commands.QueryCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * This is an implementation of SalesService interface which acts as a service layer between the controller and the
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
@Service
@Slf4j
public class SalesServiceImpl implements SalesService {

    private final QueryBuilderService queryBuilderService;

    private final SalesRepository salesRepository;

    private String value;

    public SalesServiceImpl(QueryBuilderService queryBuilderService, SalesRepository salesRepository) {
        this.queryBuilderService = queryBuilderService;
        this.salesRepository = salesRepository;
    }

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
    @Override
    public Flux<SaleDocument> getSales(QueryCommand queryCommand) {
        log.debug("A sale get request is made with queryCommand: {}", queryCommand);
        return this.salesRepository.getSales(queryBuilderService.buildQuery(queryCommand));
    }

    @Override
    public Mono<SaleDocument> saveSale(SaleDocument sale) {
        log.debug("A sale update request is made with document: {}", sale);
        return this.salesRepository.saveSale(sale);
    }

    @Override
    public Flux<SaleDocument> saveSales(List<SaleDocument> sales) {
        log.debug("A sales update request is made with documents: {}", sales.toString());
        return this.salesRepository.saveSales(sales);
    }

    @Override
    public Mono<SaleDocument> updateSale(SaleDocument sale) {
        log.debug("A sale update request is made with document: {}", sale);
        return this.salesRepository.updateSale(sale);
    }

    @Override
    public Flux<SaleDocument> updateSales(List<SaleDocument> sales) {
        log.debug("A sales update request is made with documents: {}", sales.toString());
        return this.salesRepository.updateSales(sales);
    }

    @Override
    public Mono<DeleteResult> deleteSale(SaleDocument sale) {
        log.debug("A sale delete request is made with document: {}", sale);
        return this.salesRepository.deleteSale(sale);
    }


    @Override
    public Mono<DeleteResult> deleteSales(QueryCommand queryCommand) {
        log.debug("A sales delete request is made with queryCommand: {}", queryCommand);
        return this.salesRepository.deleteSales(queryBuilderService.buildQuery(queryCommand));
    }
}
