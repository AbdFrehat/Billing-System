package com.sales.query.get.controller;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.entites.Sale;
import com.sales.query.shared.models.repository.SalesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GetSaleController {

    private final SalesRepository salesRepository;

    public GetSaleController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @PostMapping
    ResponseEntity<Flux<Sale>> getSales(@RequestBody QueryCommand queryCommand) {
        return new ResponseEntity<>(salesRepository.getSales(queryCommand), HttpStatus.OK);
    }
}
