package com.selling.system.modify.shared.sales.client;

import com.selling.system.shared.module.models.commands.ModifyCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KafkaProducerClient {

    private final WebClient webClient;

    @Value("${config.services.context-path.kafka-sale-producer-ms}")
    private String kafkaProducerUrl;


    public KafkaProducerClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<ResponseEntity<Void>> send(ModifyCommand modifyCommand) {
        return webClient
                .post()
                .uri(kafkaProducerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(modifyCommand)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    return Mono.error(new Throwable());
                })
                .toBodilessEntity();
    }

}
