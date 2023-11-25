package com.selling.system.query.sales.opt.get.controller;


import com.selling.system.query.shared.module.entites.QueryCommandDTO;
import com.selling.system.query.shared.module.service.QueryResponseService;
import com.selling.system.shared.module.models.responses.QueryResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class GetSaleController {

    private final QueryResponseService queryResponseService;


    public GetSaleController(QueryResponseService queryResponseService) {
        this.queryResponseService = queryResponseService;
    }

    @PostMapping
    Mono<ResponseEntity<QueryResponse>> getSales(@RequestBody @Valid QueryCommandDTO queryCommand) {
        log.info("getSales endpoint is called with {} command.", queryCommand);
        return queryResponseService.buildQueryResponse(queryCommand);
    }
}
