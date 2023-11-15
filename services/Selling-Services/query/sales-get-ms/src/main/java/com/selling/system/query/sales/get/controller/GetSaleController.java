package com.selling.system.query.sales.get.controller;

import com.selling.system.query.shared.module.service.SalesService;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GetSaleController {

    private final SalesService salesService;


    public GetSaleController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    ResponseEntity<QueryResponse> getSales(@RequestBody @Valid QueryCommand queryCommand) {
        return ResponseEntity.ok().body(QueryResponse.builder().data(salesService.getSales(queryCommand)).build());
    }
}
