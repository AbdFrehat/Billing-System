package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.selling.system.auth.shared.module.constants.Columns.Fields.COUNT;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;

public interface ProfileMapper {
    static Mono<Profile> fromRows(List<Map<String, Object>> rows) {
        if (rows.get(0).get(PROFILE_ID) != null)
            return Flux.fromIterable(rows).flatMap(AuthorityMapper::fromRow)
                    .collect(Collectors.toSet())
                    .map($ -> Profile.builder()
                            .profileId((int) rows.get(0).get(PROFILE_ID))
                            .profileName((String) rows.get(0).get(PROFILE_NAME))
                            .authorities($)
                            .build());
        return Mono.just(Profile.builder().profileName("undefined").build());
    }


}
