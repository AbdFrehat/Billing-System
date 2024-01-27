package com.selling.system.auth.shared.module.builder.api;

import com.selling.system.auth.shared.module.models.enums.Query;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityData;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface QueryBuilder {

    Mono<String> buildProfileAuthoritiesQuery(Query query, Set<String> authorities);

    Mono<String> buildInsertProfileAuthoritiesQuery(String query, Set<String> authorities);

    Mono<String> buildDeleteProfileAuthoritiesQuery(String query, Set<String> authorities);

    Mono<String> buildInsertAuthoritiesQuery(String query, Set<AuthorityData> authorities);
}
