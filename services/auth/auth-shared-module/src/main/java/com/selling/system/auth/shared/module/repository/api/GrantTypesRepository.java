package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.GrantType;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GrantTypesRepository {

    Flux<GrantType> retrieveGrantTypes();

    Mono<Long> createGrantType(GrantTypes grantTypes);

    Mono<Long> deleteGrantType(GrantTypes grantTypes);

}
