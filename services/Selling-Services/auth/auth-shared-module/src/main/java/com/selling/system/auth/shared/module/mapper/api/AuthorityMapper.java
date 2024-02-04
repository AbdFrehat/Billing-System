package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Group;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_NAME;
import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_NAME;

public interface AuthorityMapper {

    static Mono<Authority> fromRow(Map<String, Object> row) {
        if (row.get(AUTHORITY_ID) != null)
            return Mono.just(
                    Authority.builder()
                            .authorityId((int) row.get(AUTHORITY_ID))
                            .authorityName((String) row.get(AUTHORITY_NAME))
                            .group(Group.builder()
                                    .groupId((int) row.get(GROUP_ID))
                                    .groupName((String) row.get(GROUP_NAME))
                                    .build())
                            .build()
            );
        return Mono.empty();
    }
}
