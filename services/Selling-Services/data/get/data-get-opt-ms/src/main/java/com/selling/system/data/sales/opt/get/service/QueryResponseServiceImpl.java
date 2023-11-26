package com.selling.system.data.sales.opt.get.service;

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
        if (queryCommand.isCount()) {
            return QueryResponseMapperUtil.mapMonoToResponse(salesService.count(queryCommand));
        }
        log.info("GET_SALES Command is called");
        return QueryResponseMapperUtil.mapFluxToResponse(salesService.getSales(queryCommand));

    }}
