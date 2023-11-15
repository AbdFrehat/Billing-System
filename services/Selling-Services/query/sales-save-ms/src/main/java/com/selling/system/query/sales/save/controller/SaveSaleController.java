package com.selling.system.query.sales.save.controller;

import com.selling.system.query.shared.module.entites.QueryCommandDTO;
import com.selling.system.query.shared.module.entites.SaleDocument;
import com.selling.system.query.shared.module.service.SalesService;
import com.selling.system.shared.module.models.exceptions.QueryMethodNotFoundException;
import com.selling.system.shared.module.models.responses.QueryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class SaveSaleController {

    private final SalesService salesService;

    public SaveSaleController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    Mono<ResponseEntity<QueryResponse>> saveSale(@RequestBody @Valid QueryCommandDTO queryCommand) {
        switch (queryCommand.getQueryMethod()) {
            case SAVE_SALE -> {
                return salesService.saveSale((SaleDocument) queryCommand.getPayload())
                        .map(saleDocument -> QueryResponse.builder().data(saleDocument).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
            case SAVE_SALES -> {
                return salesService.saveSales((List<SaleDocument>) queryCommand.getPayload())
                        .collectList()
                        .map(saleDocuments -> QueryResponse.builder().data(saleDocuments).build())
                        .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
            }
        }
        throw new QueryMethodNotFoundException("The provided query method is not supported.");
    }
}