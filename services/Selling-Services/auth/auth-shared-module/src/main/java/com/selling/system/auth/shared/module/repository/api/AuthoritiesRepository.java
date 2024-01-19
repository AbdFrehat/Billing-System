package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.Authority;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends ReactiveCrudRepository<Authority, Integer> {
}
