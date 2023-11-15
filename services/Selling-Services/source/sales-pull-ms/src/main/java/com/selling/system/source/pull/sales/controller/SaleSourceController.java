package com.selling.system.source.pull.sales.controller;

import com.selling.system.source.random.generator.sales.service.SaleGeneratorService;
import com.selling.system.source.random.generator.sales.model.entities.Sale;
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

    private final SaleGeneratorService saleGeneratorService;

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
