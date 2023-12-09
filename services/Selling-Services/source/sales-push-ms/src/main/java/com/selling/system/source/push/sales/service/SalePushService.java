package com.selling.system.source.push.sales.service;

import com.selling.system.shared.module.models.commands.ModifyCommand;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.source.random.generator.sales.service.SaleGeneratorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SalePushService {

    @Value("${emit.duration}")
    private long duration;

    private final WebClient webClient;

    private final SaleGeneratorService saleGeneratorService;

    public SalePushService(SaleGeneratorService saleGeneratorService, WebClient.Builder webClientBuilder,
                           @Value("${config.services.context-path.kafka-sale-producer-ms}") String kafkaSaleProducerMsContextPath) {
        this.saleGeneratorService = saleGeneratorService;
        this.webClient = webClientBuilder.baseUrl(kafkaSaleProducerMsContextPath).build();
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
                .bodyValue(
                        ModifyCommand.builder()
                                .commandType(CommandType.SAVE_SALE)
                                .data(saleGeneratorService.generateRandomSale())
                                .build()
                )
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
