package com.selling.system.data.update.manager.sales.service;

import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.shared.module.models.responses.DataResponse;
import org.springframework.http.HttpStatusCode;
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
     * This method takes the request object, build the web client and select the uri based on the queryMethod inside {@link DataCommand}
     *
     * @param dataCommand represents the request object to be sent to the data microservices
     * @return {@link Mono}<{@link DataResponse}> which represents the retrieved data from the query services.
     */
    @Override
    public Mono<DataResponse> sendRequest(DataCommand dataCommand) {
        return webClient
                .post()
                .uri(getUri(dataCommand.getCommandType()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataCommand)
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(dataCommand.getCommandType().name()))
                .bodyToMono(DataResponse.class);
    }

    private String getUri(CommandType commandType) {
        return switch (commandType) {
            case UPDATE_SALE -> servicesContextPath.get("data-update-ms");
            case UPDATE_SALES -> servicesContextPath.get("data-update-multi-ms");
            default -> throw new IllegalArgumentException();
        };
    }
}
