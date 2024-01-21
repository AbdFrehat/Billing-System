package com.selling.system.auth.shared.module.builder.api;

import com.selling.system.auth.shared.module.models.enums.Query;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface QueryBuilder {

    Mono<String> buildInsertProfileAuthoritiesQuery(String query, Set<String> authorities);
}
