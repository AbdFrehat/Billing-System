package com.orderizer.core.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderizer.core.exceptions.client.*;
import com.orderizer.core.models.responses.ErrorResponse;
import com.orderizer.core.exceptions.general.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.orderizer.core.utils.JsonUtil.fromJson;


public class ClientExceptionHandler implements Function<ClientResponse, Mono<? extends Throwable>> {

    private final String serviceName;

    public ClientExceptionHandler(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public Mono<? extends Throwable> apply(ClientResponse clientResponse) {
        ObjectMapper mapper = new ObjectMapper();
        return clientResponse.bodyToMono(String.class).flatMap(message -> Mono.error(
                switch (clientResponse.statusCode().value()) {
                    case 400 ->
                            new BadRequestException(serviceName + " returns a bad request", HttpStatus.BAD_REQUEST.value(),
                                    fromJson(message, ErrorResponse.class));
                    case 401 ->
                            new UnauthorizedCallException(serviceName + " is rejected the connection", HttpStatus.UNAUTHORIZED.value(),
                                    fromJson(message, ErrorResponse.class));
                    case 403 ->
                            new ForbiddenCallException(serviceName + " is forbidden the connection" + message, HttpStatus.FORBIDDEN.value(),
                                    fromJson(message, ErrorResponse.class));
                    case 404 ->
                            new ServiceNotFoundException(serviceName + " is not found", HttpStatus.NOT_FOUND.value(),
                                    fromJson(message, ErrorResponse.class));
                    case 500 ->
                            new InternalServerErrorException(serviceName + " is suffered from an internal exception", HttpStatus.NOT_FOUND.value(),
                                    fromJson(message, ErrorResponse.class));
                    case 503 ->
                            new ServiceUnAvailableException(serviceName + " is not available", HttpStatus.NOT_FOUND.value());
                    default -> new ClientException(message, clientResponse.statusCode().value());
                }));
    }
}
