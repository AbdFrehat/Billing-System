package com.selling.system.data.manager.sales.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * This service class implements {@link SalesClientService} which the main goal for it to pass the query command based on the
 * query method to the right service.
 * @since 1.0
 * @author Abd Frehat
 */
@Service
public class SalesClientServiceImpl implements SalesClientService {

    private final WebClient webClient;

    public SalesClientServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * This method takes the request object, build the web client and select the uri based on the queryMethod inside {@link QueryCommand}
     * @param queryCommand represents the request object to be sent to the data microservices
     * @return {@link Mono}<{@link QueryResponse}> which represents the retrieved data from the query services.
     */
    @Override
    public Mono<QueryResponse> sendRequest(QueryCommand queryCommand) {
        return webClient
                .post()
                .uri("http:" + queryCommand.getQueryMethod().getValue() + "v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(queryCommand)
                .retrieve()
                .bodyToMono(QueryResponse.class);
    }
}
