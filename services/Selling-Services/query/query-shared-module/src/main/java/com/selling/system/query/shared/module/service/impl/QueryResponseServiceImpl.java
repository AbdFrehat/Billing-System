package com.selling.system.query.shared.module.service.impl;

import com.selling.system.query.shared.module.entites.QueryCommandDTO;
import com.selling.system.query.shared.module.entites.SaleDocument;
import com.selling.system.query.shared.module.service.QueryResponseService;
import com.selling.system.query.shared.module.service.SalesService;
import com.selling.system.shared.module.models.exceptions.QueryMethodNotFoundException;
import com.selling.system.shared.module.models.responses.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class QueryResponseServiceImpl implements QueryResponseService {

    private final SalesService salesService;

    public QueryResponseServiceImpl(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public Mono<ResponseEntity<QueryResponse>> buildQueryResponse(QueryCommandDTO queryCommand) {
        switch (queryCommand.getQueryMethod()) {
            case UPDATE_SALE -> {
                log.info("UPDATE_SALE Command is called");
                return salesService.updateSale((SaleDocument) queryCommand.getPayload())
                        .map(saleDocument -> QueryResponse.builder().data(saleDocument).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case UPDATE_SALES -> {
                log.info("UPDATE_SALES Command is called");
                return salesService.updateSales((List<SaleDocument>) queryCommand.getPayload())
                        .collectList()
                        .map(saleDocuments -> QueryResponse.builder().data(saleDocuments).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case GET_SALES -> {
                log.info("GET_SALES Command is called");
                return salesService.getSales(queryCommand)
                        .collectList()
                        .map(saleDocuments -> QueryResponse.builder().data(saleDocuments).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case SAVE_SALE -> {
                log.info("SAVE_SALE Command is called");
                return salesService.saveSale((SaleDocument) queryCommand.getPayload())
                        .map(saleDocument -> QueryResponse.builder().data(saleDocument).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case SAVE_SALES -> {
                log.info("SAVE_SALES Command is called");
                return salesService.saveSales((List<SaleDocument>) queryCommand.getPayload())
                        .collectList()
                        .map(saleDocuments -> QueryResponse.builder().data(saleDocuments).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case DELETE_SALE -> {
                log.info("DELETE_SALE Command is called");
                return salesService.deleteSale((SaleDocument) queryCommand.getPayload())
                        .map(deleteResult -> QueryResponse.builder().data(deleteResult).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case DELETE_SALES -> {
                log.info("DELETE_SALES Command is called");
                return salesService.deleteSales(queryCommand)
                        .map(deleteResult -> QueryResponse.builder().data(deleteResult).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
        }
        throw new QueryMethodNotFoundException("The provided query method is not supported.");
    }
}
