package com.selling.system.query.manager.sales.controller;

import com.selling.system.query.manager.sales.service.SalesClientService;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SalesRouterController {


    private final SalesClientService salesClientService;

    public SalesRouterController(SalesClientService salesClientService) {
        this.salesClientService = salesClientService;
    }

    @PostMapping
    public Mono<ResponseEntity<QueryResponse>> routeQueryServe(@RequestBody @Valid QueryCommand queryCommand) {
        return salesClientService.sendRequest(queryCommand)
                .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
    }


}
