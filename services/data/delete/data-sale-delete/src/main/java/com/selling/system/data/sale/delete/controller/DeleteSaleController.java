package com.selling.system.data.sale.delete.controller;

import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.DataResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class DeleteSaleController {

    private final QueryResponseService queryResponseService;

    public DeleteSaleController(QueryResponseService queryResponseService) {
        this.queryResponseService = queryResponseService;
    }

    @PostMapping
    Mono<ResponseEntity<DataResponse>> deleteSale(@RequestBody @Valid DataCommand dataCommand) {
        log.info("deleteSale endpoint is called with {} command.", dataCommand);
        return queryResponseService.buildQueryResponse(dataCommand);
    }
}
