package com.selling.system.shared.module.handlers;

import com.selling.system.shared.module.exceptions.client.*;
import com.selling.system.shared.module.exceptions.general.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;


public class ClientExceptionHandler implements Function<ClientResponse, Mono<? extends Throwable>> {

    private final String serviceName;

    public ClientExceptionHandler(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public Mono<? extends Throwable> apply(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class).flatMap(message -> Mono.error(switch (clientResponse.statusCode().value()) {
            case 400 ->
                    new BadRequestException(serviceName + " is received a bad request: " + message, HttpStatus.BAD_REQUEST.value());
            case 401 ->
                    new UnauthorizedCallException(serviceName + " is rejected the connection: " + message, HttpStatus.UNAUTHORIZED.value());
            case 403 ->
                    new ForbiddenCallException(serviceName + " is forbidden the connection: " + message, HttpStatus.FORBIDDEN.value());
            case 404 ->
                    new ServiceNotFoundException(serviceName + " is not found: " + message, HttpStatus.NOT_FOUND.value());
            case 500 ->
                    new InternalServerErrorException(serviceName + " is suffered from an internal exception: " + message, HttpStatus.NOT_FOUND.value());
            case 503 ->
                    new ServiceUnAvailableException(serviceName + " is not available: " + message, HttpStatus.NOT_FOUND.value());
            default -> new ClientException(message, clientResponse.statusCode().value());
        }));
    }
}
