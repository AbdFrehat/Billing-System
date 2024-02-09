package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Authority;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_NAME;

public interface AuthorityMapper {

    static Mono<Authority> fromRow(Map<String, Object> row) {
        if (row.get(AUTHORITY_ID) != null)
            return GroupMapper.fromRow(row).map($ -> Authority.builder()
                    .authorityId((int) row.get(AUTHORITY_ID))
                    .authorityName((String) row.get(AUTHORITY_NAME))
                    .group($)
                    .build());
        return Mono.empty();
    }
}
