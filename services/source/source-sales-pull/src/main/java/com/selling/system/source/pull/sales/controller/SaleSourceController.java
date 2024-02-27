package com.selling.system.source.pull.sales.controller;

import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.source.random.generator.sales.service.SaleGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class SaleSourceController {

    @Value("${emit.duration}")
    private long duration;
    @Autowired
    private Date date;
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

    @GetMapping("/date")
    public Date getDate() {
        return date;
    }

    @GetMapping("/local")
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

}
