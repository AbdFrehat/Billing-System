package com.selling.system.query.manager.sales.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.query.manager.sales.models.entites.Sale;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SalesClientServiceImpl implements SalesClientService {

    private final WebClient webClient;

    public SalesClientServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<QueryResponse> sendRequest(QueryCommand queryCommand) {
        return webClient
                .post()
                .uri("http://SALES-GET-MS/selling/query/get/sale/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(queryCommand), QueryCommand.class)
                .retrieve()
                .bodyToFlux(Sale.class)
                .collectList()
                .map(sales -> QueryResponse.builder().data(sales).build());
    }
}
