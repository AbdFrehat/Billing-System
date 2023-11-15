package com.selling.system.query.sales.get.controller;

import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.query.shared.module.service.SalesService;
import com.selling.system.shared.module.models.commands.QueryCommand;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class GetSaleController {

    private final SalesService salesService;


    public GetSaleController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    Mono<ResponseEntity<Flux<Sale>>> getSales(@RequestBody @Valid Mono<QueryCommand> queryCommand) {
        return queryCommand
                .flatMapMany(salesService::getSales)
                .collectList()
                .map(sales -> ResponseEntity.ok().body(Flux.fromIterable(sales)))
                .onErrorResume(e -> {
                    log.error("Error processing request", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
                });
    }
}
