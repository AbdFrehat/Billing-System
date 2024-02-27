package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.GrantType;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.GrantType.GRANT_ID;
import static com.selling.system.auth.shared.module.constants.Columns.GrantType.GRANT_TYPE;

public interface GrantTypeMapper {

    static Mono<GrantType> fromRow(Map<String, Object> row) {
        return Mono.just(GrantType.builder()
                .grantId((int) row.get(GRANT_ID))
                .grantTypes(GrantTypes.valueOf((String) row.get(GRANT_TYPE)))
                .build());
    }
}
