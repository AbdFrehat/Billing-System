package com.selling.sales.kafka.sales.consumer.client;

import com.selling.system.shared.module.models.commands.QueryCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DataManagerClient {

    public WebClient webClient;

    @Value("${config.services.context-path.data-manager-ms}")
    private String dataManagerMsContextPath;

    public DataManagerClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<ResponseEntity<Void>> saveSales(QueryCommand queryCommand) {
        return webClient
                .post()
                .uri(dataManagerMsContextPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(queryCommand)
                .retrieve()
                .toBodilessEntity();
    }

}
