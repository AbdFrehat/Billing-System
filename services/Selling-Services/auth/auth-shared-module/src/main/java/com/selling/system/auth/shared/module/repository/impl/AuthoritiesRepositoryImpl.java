package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.builder.api.QueryBuilder;
import com.selling.system.auth.shared.module.mapper.api.AuthorityMapper;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityData;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.AuthoritiesRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_NAME;
import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_NAME;
import static com.selling.system.auth.shared.module.models.enums.Query.*;

@Repository
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository {
    private final DatabaseClient client;

    private final QueryProvider provider;

    private final QueryBuilder builder;

    public AuthoritiesRepositoryImpl(DatabaseClient client, QueryProvider provider, QueryBuilder builder) {
        this.client = client;
        this.provider = provider;
        this.builder = builder;
    }

    @Override
    public Flux<Authority> retrieveAllAuthorities() {
        return client.sql(provider.provide(RETRIEVE_ALL_AUTHORITIES))
                .fetch()
                .all()
                .flatMap(AuthorityMapper::fromRow);
    }

    @Override
    public Mono<Long> updateAuthorityName(String authorityName, String updatedAuthorityName) {
        return client.sql(provider.provide(UPDATE_AUTHORITY_NAME))
                .bind(AUTHORITY_NAME, authorityName)
                .bind("update_authority_name", updatedAuthorityName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveAuthorities(Set<AuthorityData> authorities) {
        Mono<String> builtQuery = builder.buildInsertAuthoritiesQuery(provider.provide(ADD_AUTHORITY), authorities);
        return builtQuery
                .flatMap($ -> {
                    DatabaseClient.GenericExecuteSpec genericExecuteSpec = client.sql($);
                    Iterator<AuthorityData> iterator = authorities.iterator();
                    int counter = 1;
                    while (iterator.hasNext()) {
                        AuthorityData authorityData = iterator.next();
                        genericExecuteSpec = genericExecuteSpec
                                .bind(AUTHORITY_NAME + counter, authorityData.getAuthorityName())
                                .bind(GROUP_NAME + counter++, authorityData.getGroupName());
                    }
                    return genericExecuteSpec.fetch().rowsUpdated();
                });
    }
}
