package com.selling.system.data.save.manager.sales.service;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.enums.QueryMethod;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * This service class implements {@link SalesClientService} which the main goal for it to pass the query command based on the
 * query method to the right service.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@Service
public class SalesClientServiceImpl implements SalesClientService {

    private final WebClient webClient;

    private final Map<String, String> servicesContextPath;

    public SalesClientServiceImpl(WebClient.Builder webClientBuilder, Map<String, String> servicesContextPath) {
        this.webClient = webClientBuilder.build();
        this.servicesContextPath = servicesContextPath;
    }

    /**
     * This method takes the request object, build the web client and select the uri based on the queryMethod inside {@link QueryCommand}
     *
     * @param queryCommand represents the request object to be sent to the data microservices
     * @return {@link Mono}<{@link QueryResponse}> which represents the retrieved data from the query services.
     */
    @Override
    public Mono<QueryResponse> sendRequest(QueryCommand queryCommand) {
        return webClient
                .post()
                .uri(getUri(queryCommand.getQueryMethod()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(queryCommand)
                .retrieve()
                .bodyToMono(QueryResponse.class);
    }

    private String getUri(QueryMethod queryMethod) {
        return switch (queryMethod) {
            case SAVE_SALE -> servicesContextPath.get("data-save-ms");
            case SAVE_SALES -> servicesContextPath.get("data-save-multi-ms");
            default -> throw new IllegalArgumentException();
        };
    }
}
