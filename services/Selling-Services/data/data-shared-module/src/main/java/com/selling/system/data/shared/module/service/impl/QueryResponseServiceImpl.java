package com.selling.system.data.shared.module.service.impl;

import com.selling.system.data.shared.module.entites.QueryCommandDTO;
import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.data.shared.module.service.SalesService;
import com.selling.system.data.shared.module.util.QueryResponseMapperUtil;
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
                return QueryResponseMapperUtil.mapMonoToResponse(salesService.updateSale((SaleDocument) queryCommand.getPayload()));
            }
            case UPDATE_SALES -> {
                log.info("UPDATE_SALES Command is called");
                return QueryResponseMapperUtil.mapFluxToResponse(salesService.updateSales((List<SaleDocument>) queryCommand.getPayload()));
            }
            case GET_SALES, GET_OPT_SALES, GET_FREE_SALES -> {
                if (queryCommand.isCount()) {
                    return QueryResponseMapperUtil.mapMonoToResponse(salesService.count(queryCommand));
                }
                log.info("GET_SALES Command is called");
                return QueryResponseMapperUtil.mapFluxToResponse(salesService.getSales(queryCommand));
            }
            case SAVE_SALE -> {
                log.info("SAVE_SALE Command is called");
                return QueryResponseMapperUtil.mapMonoToResponse(salesService.saveSale((SaleDocument) queryCommand.getPayload()));
            }
            case SAVE_SALES -> {
                log.info("SAVE_SALES Command is called");
                return QueryResponseMapperUtil.mapFluxToResponse(salesService.saveSales((List<SaleDocument>) queryCommand.getPayload()));
            }
            case DELETE_SALE -> {
                log.info("DELETE_SALE Command is called");
                return QueryResponseMapperUtil.mapMonoToResponse(salesService.deleteSale((SaleDocument) queryCommand.getPayload()));
            }
            case DELETE_SALES -> {
                if (queryCommand.isCount()) {
                    return QueryResponseMapperUtil.mapMonoToResponse(salesService.count(queryCommand));
                }
                log.info("DELETE_SALES Command is called");
                return QueryResponseMapperUtil.mapMonoToResponse(salesService.deleteSales(queryCommand));
            }
        }
        throw new QueryMethodNotFoundException("The provided query method is not supported.");
    }
}
