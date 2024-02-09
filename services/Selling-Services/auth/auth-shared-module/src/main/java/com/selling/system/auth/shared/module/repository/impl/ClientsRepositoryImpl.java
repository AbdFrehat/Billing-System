package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.ClientMapper;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.entities.Client;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.ClientsRepository;
import com.selling.system.shared.module.exceptions.business.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.Client.*;
import static com.selling.system.auth.shared.module.constants.Columns.GrantType.GRANT_TYPE;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;
import static com.selling.system.auth.shared.module.models.enums.Query.*;

@Repository
@RequiredArgsConstructor
public class ClientsRepositoryImpl implements ClientsRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    @Override
    public Flux<Client> retrieveAllClients() {
        return client.sql(provider.provide(RETRIEVE_ALL_CLIENTS))
                .fetch()
                .all()
                .bufferUntilChanged($ -> $.get(CLIENT_ID))
                .flatMap(ClientMapper::fromRows);
    }

    @Override
    public Mono<Client> retrieveClientById(String clientId) {
        return client.sql(provider.provide(RETRIEVE_CLIENT))
                .bind(CLIENT_ID, clientId)
                .fetch()
                .all()
                .bufferUntilChanged($ -> $.get(CLIENT_ID))
                .flatMap(ClientMapper::fromRows)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new ClientNotFoundException()));
    }

    @Override
    public Mono<Long> updateClientProfile(String clientId, String profileName) {
        return client.sql(provider.provide(UPDATE_CLIENT_PROFILE))
                .bind(CLIENT_ID, clientId)
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> updateClientGrantType(String clientId, GrantTypes grantTypes) {
        return client.sql(provider.provide(UPDATE_CLIENT_GRANT_TYPE))
                .bind(CLIENT_ID, clientId)
                .bind(GRANT_TYPE, grantTypes.name())
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> deleteClient(String clientId) {
        return client.sql(provider.provide(DELETE_CLIENT))
                .bind(CLIENT_ID, clientId)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> createClient(String clientId, String clientName, String clientSecret,
                                   String profileName, GrantTypes grantTypes) {
        return client.sql(provider.provide(ADD_CLIENT))
                .bind(CLIENT_ID, clientId)
                .bind(CLIENT_NAME, clientName)
                .bind(CLIENT_SECRET, clientSecret)
                .bind(PROFILE_NAME, profileName)
                .bind(GRANT_TYPE, grantTypes.name())
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Boolean> isClientExists(String clientName) {
        return client.sql(provider.provide(IS_CLIENT_NAME_EXISTS))
                .bind(CLIENT_NAME, clientName.toLowerCase())
                .fetch()
                .first()
                .flatMap(Mapper::fromCountQueryRow);
    }
}
