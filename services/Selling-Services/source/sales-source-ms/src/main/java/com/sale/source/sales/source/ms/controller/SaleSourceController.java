package com.sale.source.sales.source.ms.controller;

import com.sale.source.sales.source.ms.model.entities.Sale;
import com.sale.source.sales.source.ms.service.SaleGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class SaleSourceController {

    @Value("${emit.duration}")
    private long duration;

    private SaleGeneratorService saleGeneratorService;

    public SaleSourceController(SaleGeneratorService saleGeneratorService) {
        this.saleGeneratorService = saleGeneratorService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Sale>> emitSale() {
        return Flux.interval(Duration.ofMillis(duration))
                .map(sequence -> ServerSentEvent.<Sale>builder()
                        .data(saleGeneratorService.generateRandomSale())
                        .build());
    }
}
