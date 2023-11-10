package com.sales.query.get.controller;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.entites.Sale;
import com.sales.query.shared.models.service.SalesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GetSaleController {

    private final SalesService salesService;

    public GetSaleController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    ResponseEntity<Flux<Sale>> getSales(@RequestBody @Valid QueryCommand queryCommand) {
        return new ResponseEntity<>(salesService.getSales(queryCommand), HttpStatus.OK);
    }
}
