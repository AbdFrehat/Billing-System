package com.selling.system.auth.manager.service.client.api;

import com.selling.system.auth.manager.model.client.response.ClientResponse;
import reactor.core.publisher.Mono;

public interface ClientsServiceClient {

    Mono<ClientResponse> retrieveClientById(String clientId);

}
