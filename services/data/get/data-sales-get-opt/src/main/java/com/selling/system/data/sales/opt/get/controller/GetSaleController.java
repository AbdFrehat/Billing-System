package com.selling.system.data.sales.opt.get.controller;


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
public class GetSaleController {

    private final QueryResponseService queryResponseService;


    public GetSaleController(QueryResponseService queryResponseService) {
        this.queryResponseService = queryResponseService;
    }

    @PostMapping
    Mono<ResponseEntity<DataResponse>> getSales(@RequestBody @Valid DataCommand dataCommand) {
        log.info("getSales endpoint is called with {} command.", dataCommand);
        return queryResponseService.buildQueryResponse(dataCommand);
    }
}
