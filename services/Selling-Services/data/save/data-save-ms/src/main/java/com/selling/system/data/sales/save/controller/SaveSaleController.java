package com.selling.system.data.sales.save.controller;

import com.selling.system.data.shared.module.entites.QueryCommandDTO;
import com.selling.system.data.shared.module.service.QueryResponseService;
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
public class SaveSaleController {

    private final QueryResponseService queryResponseService;

    public SaveSaleController(QueryResponseService queryResponseService) {
        this.queryResponseService = queryResponseService;
    }

    @PostMapping
    Mono<ResponseEntity<QueryResponse>> saveSale(@RequestBody @Valid QueryCommandDTO queryCommand) {
        log.info("saveSale endpoint is called with {} command.", queryCommand);
        return queryResponseService.buildQueryResponse(queryCommand);
    }
}