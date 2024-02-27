package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.constants.Columns;
import com.selling.system.auth.shared.module.mapper.api.GrantTypeMapper;
import com.selling.system.auth.shared.module.models.entities.GrantType;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.GrantTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.models.enums.Query.*;

@Repository
@RequiredArgsConstructor
public class GrantTypesRepositoryImpl implements GrantTypesRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    @Override
    public Flux<GrantType> retrieveGrantTypes() {
        return client.sql(provider.provide(RETRIEVE_GRANT_TYPES))
                .fetch()
                .all()
                .flatMap(GrantTypeMapper::fromRow);
    }

    @Override
    public Mono<Long> createGrantType(GrantTypes grantTypes) {
        return client.sql(provider.provide(ADD_GRANT_TYPE))
                .bind(Columns.GrantType.GRANT_TYPE, grantTypes.name())
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> deleteGrantType(GrantTypes grantTypes) {
        return client.sql(provider.provide(DELETE_GRANT_TYPE))
                .bind(Columns.GrantType.GRANT_TYPE, grantTypes.name())
                .fetch()
                .rowsUpdated();
    }
}
