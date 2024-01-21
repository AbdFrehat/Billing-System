package com.selling.system.auth.shared.module.builder.impl;

import com.selling.system.auth.shared.module.builder.api.QueryBuilder;
import com.selling.system.auth.shared.module.models.enums.Query;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.shared.module.exceptions.Technical.AuthoritiesEmptyException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class QueryBuilderImpl implements QueryBuilder {

    private final QueryProvider provider;

    public QueryBuilderImpl(QueryProvider provider) {
        this.provider = provider;
    }

    @Override
    public Mono<String> buildInsertProfileAuthoritiesQuery(String query, Set<String> authorities) {
        StringBuilder queryBuilder = new StringBuilder();
        if (authorities == null || authorities.isEmpty()) {
            return Mono.error(AuthoritiesEmptyException::new);
        }
        int counter = 1;
        queryBuilder.append(query);
        String retrieveQueryKeys = provider.provider(Query.RETRIEVE_PROFILE_AUTHORITIES_KEYS);
        String replacedQuery = String.format(retrieveQueryKeys, ":profile_name", ":authority_name_" + counter++);
        queryBuilder.append(replacedQuery);
        while (counter <= authorities.size()) {
            queryBuilder.append("UNION ALL").append(System.lineSeparator());
            replacedQuery = String.format(retrieveQueryKeys, ":profile_name", ":authority_name_" + counter++);
            queryBuilder.append(replacedQuery);
        }
        return Mono.just(queryBuilder.toString());
    }
}
