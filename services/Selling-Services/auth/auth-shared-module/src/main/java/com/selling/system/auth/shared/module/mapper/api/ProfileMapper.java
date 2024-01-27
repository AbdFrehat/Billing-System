package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProfileMapper {
    static Mono<Profile> fromRows(List<Map<String, Object>> rows) {
        return Flux.fromIterable(rows).flatMap(AuthorityMapper::fromRow)
                .collect(Collectors.toSet())
                .map($ -> Profile.builder()
                        .profileId((int) rows.get(0).get("profile_id"))
                        .profileName((String) rows.get(0).get("profile_name"))
                        .authorities($)
                        .build());
    }

    static Mono<ProfileNameExistenceResponse> fromCountQueryRow(Map<String, Object> row) {
        return Mono.just(ProfileNameExistenceResponse.builder()
                .exists(((long) row.get("profile_count")) > 0L)
                .build());
    }
}
