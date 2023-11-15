package com.selling.system.source.push.sales.service;

import com.selling.system.source.random.generator.sales.service.SaleGeneratorService;
import com.selling.system.source.random.generator.sales.model.entities.Sale;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SalePushService {

    @Value("${emit.duration}")
    private long duration;

    private final WebClient webClient;

    private final SaleGeneratorService saleGeneratorService;

    public SalePushService(SaleGeneratorService saleGeneratorService, WebClient.Builder webClientBuilder) {
        this.saleGeneratorService = saleGeneratorService;
        this.webClient = webClientBuilder.baseUrl("http://kafka-sale-producer-ms//selling/persistence/sale/v1/").build();
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::pushSaleToKafkaProducer, 0, duration, TimeUnit.MILLISECONDS);
    }


    public void pushSaleToKafkaProducer() {
        webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(saleGeneratorService.generateRandomSale()), Sale.class)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
