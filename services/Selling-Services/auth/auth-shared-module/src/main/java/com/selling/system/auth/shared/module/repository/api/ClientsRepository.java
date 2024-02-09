package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.Client;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientsRepository {

    Flux<Client> retrieveAllClients();

    Mono<Client> retrieveClientById(String clientId);

    Mono<Long> updateClientProfile(String clientId, String profileName);

    Mono<Long> updateClientGrantType(String clientId, GrantTypes grantTypes);

    Mono<Long> deleteClient(String clientId);

    Mono<Long> createClient(String clientId, String clientName, String clientSecret, String profileName, GrantTypes grantTypes);

    Mono<Boolean> isClientExists(String clientName);

}
