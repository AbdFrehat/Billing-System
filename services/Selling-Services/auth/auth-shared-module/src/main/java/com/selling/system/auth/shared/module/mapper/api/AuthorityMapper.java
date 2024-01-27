package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Authority;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthorityMapper {

    static Mono<Authority> fromRow(Map<String, Object> row) {
        if (row.get("authority_id") != null) {
            return Mono.just(
                    Authority.builder()
                            .authorityId((int) row.get("authority_id"))
                            .authorityName((String) row.get("authority_name"))
                            .build()
            );
        } else {
            return Mono.empty();
        }
    }
}
