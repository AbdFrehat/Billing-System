package com.selling.system.data.sales.get.service;

import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.data.shared.module.service.SalesService;
import com.selling.system.data.shared.module.util.QueryResponseMapperUtil;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QueryResponseServiceImpl implements QueryResponseService {

    private final SalesService salesService;

    public QueryResponseServiceImpl(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public Mono<ResponseEntity<QueryResponse>> buildQueryResponse(DataCommand dataCommand) {
        if (dataCommand.isCount()) {
            return QueryResponseMapperUtil.mapMonoToResponse(salesService.count(dataCommand));
        }
        log.info("GET_SALES Command is called");
        return QueryResponseMapperUtil.mapFluxToResponse(salesService.getSales(dataCommand));
    }
}
