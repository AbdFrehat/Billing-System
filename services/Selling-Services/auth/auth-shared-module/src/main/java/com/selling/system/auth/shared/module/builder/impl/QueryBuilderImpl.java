package com.selling.system.auth.shared.module.builder.impl;

import com.selling.system.auth.shared.module.builder.api.QueryBuilder;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.shared.module.exceptions.Technical.AuthoritiesEmptyException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_NAME;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;
import static com.selling.system.auth.shared.module.constants.SQL.UNION_ALL;
import static com.selling.system.auth.shared.module.models.enums.Query.RETRIEVE_AUTHORITIES_KEYS;
import static com.selling.system.auth.shared.module.models.enums.Query.RETRIEVE_PROFILE_AUTHORITIES_KEYS;
import static com.selling.system.auth.shared.module.util.QueryUtil.buildBindColumnName;
import static com.selling.system.shared.module.utils.CollectionUtil.isEmpty;

@Component
public class QueryBuilderImpl implements QueryBuilder {

    private final QueryProvider provider;

    public QueryBuilderImpl(QueryProvider provider) {
        this.provider = provider;
    }

    @Override
    public Mono<String> buildInsertProfileAuthoritiesQuery(String query, Set<String> authorities) {
        if (isEmpty(authorities)) {
            return Mono.error(AuthoritiesEmptyException::new);
        }
        StringBuilder queryBuilder = new StringBuilder();
        int counter = 1;
        queryBuilder.append(query);
        String retrieveQueryKeys = provider.provide(RETRIEVE_PROFILE_AUTHORITIES_KEYS);
        String replacedQuery = String.format(retrieveQueryKeys, buildBindColumnName(AUTHORITY_NAME) + counter++);
        queryBuilder.append(replacedQuery);
        while (counter <= authorities.size()) {
            queryBuilder.append(UNION_ALL).append(System.lineSeparator());
            replacedQuery = String.format(retrieveQueryKeys, buildBindColumnName(AUTHORITY_NAME) + counter++);
            queryBuilder.append(replacedQuery);
        }
        return Mono.just(queryBuilder.toString());
    }

    @Override
    public Mono<String> buildDeleteProfileAuthoritiesQuery(String query, Set<String> authorities) {
        if (isEmpty(authorities)) {
            return Mono.error(AuthoritiesEmptyException::new);
        }
        StringBuilder authoritiesPartBuilder = new StringBuilder();
        int counter = 1;
        String retrieveAuthoritiesKeys = provider.provide(RETRIEVE_AUTHORITIES_KEYS);
        String replacedQuery = String.format(retrieveAuthoritiesKeys, buildBindColumnName(AUTHORITY_NAME) + counter++);
        authoritiesPartBuilder.append(System.lineSeparator()).append(replacedQuery);
        while (counter <= authorities.size()) {
            replacedQuery = String.format(retrieveAuthoritiesKeys, buildBindColumnName(AUTHORITY_NAME) + counter++);
            authoritiesPartBuilder.append(",").append(replacedQuery);
        }
        return Mono.just(String.format(query, authoritiesPartBuilder.toString()));
    }
}
