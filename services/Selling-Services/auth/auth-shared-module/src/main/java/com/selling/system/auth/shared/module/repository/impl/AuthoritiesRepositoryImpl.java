package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.AuthorityMapper;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.AuthoritiesRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.selling.system.auth.shared.module.models.enums.Query.RETRIEVE_ALL_AUTHORITIES;

@Repository
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository {
    private final DatabaseClient client;

    private final QueryProvider provider;

    public AuthoritiesRepositoryImpl(DatabaseClient client, QueryProvider provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public Flux<Authority> retrieveAllAuthorities() {
        return client.sql(provider.provide(RETRIEVE_ALL_AUTHORITIES))
                .fetch()
                .all()
                .flatMap(AuthorityMapper::fromRow);
    }
}
