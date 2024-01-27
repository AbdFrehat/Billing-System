package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Group;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_NAME;

public interface GroupMapper {

    static Mono<Group> fromRow(Map<String, Object> row) {
        return Mono.just(Group.builder()
                .groupId((int) row.get(GROUP_ID))
                .groupName((String) row.get(GROUP_NAME))
                .build());
    }
}
