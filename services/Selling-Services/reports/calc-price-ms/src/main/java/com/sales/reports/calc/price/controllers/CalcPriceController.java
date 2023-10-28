package com.sales.reports.calc.price.controllers;

import com.sales.reports.calc.price.models.entites.Item;
import com.sales.reports.calc.price.models.responses.CalcPriceResponse;
import com.sales.reports.calc.price.services.CalcPriceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CalcPriceController {

    private CalcPriceService calcPriceService;

    public CalcPriceController(CalcPriceService calcPriceService) {
        this.calcPriceService = calcPriceService;
    }

    @PostMapping
    public ResponseEntity<Mono<CalcPriceResponse>> getCalcPriceResponse(@RequestBody @Valid List<Item> items) {
        return new ResponseEntity(calcPriceService.buildCalcPriceResponse(items), HttpStatus.OK);
    }

}
