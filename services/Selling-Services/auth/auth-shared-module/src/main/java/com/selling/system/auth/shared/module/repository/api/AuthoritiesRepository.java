package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Repository
public interface AuthoritiesRepository {

    Flux<Authority> retrieveAllAuthorities();

    Mono<Long> updateAuthorityName(String authorityName, String updatedAuthorityName);

    Mono<Long> saveAuthorities(Set<AuthorityData> authorities);
}
