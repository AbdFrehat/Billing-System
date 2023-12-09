package com.selling.system.data.sales.multi.update.service;

import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.data.shared.module.service.SalesService;
import com.selling.system.data.shared.module.util.QueryResponseMapperUtil;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.selling.system.data.shared.module.convertors.ObjectToSalesConvertor.toSales;

@Service
@Slf4j
public class QueryResponseServiceImpl implements QueryResponseService {

    private final SalesService salesService;

    public QueryResponseServiceImpl(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public Mono<ResponseEntity<QueryResponse>> buildQueryResponse(DataCommand dataCommand) {
        log.info("UPDATE_SALES Command is called");
        return QueryResponseMapperUtil.mapFluxToResponse(salesService.updateSales(toSales(dataCommand.getPayload())));
    }
}