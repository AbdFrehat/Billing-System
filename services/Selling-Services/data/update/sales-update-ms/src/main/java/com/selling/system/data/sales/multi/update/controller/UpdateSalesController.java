package com.selling.system.data.sales.multi.update.controller;

import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.shared.module.models.commands.DataCommand;
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
public class UpdateSalesController {

    private final QueryResponseService queryResponseService;

    public UpdateSalesController(QueryResponseService queryResponseService) {
        this.queryResponseService = queryResponseService;
    }

    @PostMapping
    Mono<ResponseEntity<QueryResponse>> updateSales(@RequestBody @Valid DataCommand dataCommand) {
        log.info("updateSales endpoint is called with {} command.", dataCommand);
        return queryResponseService.buildQueryResponse(dataCommand);
    }
}
