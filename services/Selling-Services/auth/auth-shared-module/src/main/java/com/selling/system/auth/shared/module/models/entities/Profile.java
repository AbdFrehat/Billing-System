package com.selling.system.auth.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Table(name = "profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Column("profile_id")
    @Id
    private int profileId;

    @Column("profile_name")
    private String profileName;

    @Transient
    private Set<Authority> authorities;


    public static Mono<Profile> fromRows(List<Map<String, Object>> rows) {
        return Mono.just(Profile.builder()
                .profileId((int) rows.get(0).get("profile_id"))
                .profileName((String) rows.get(0).get("profile_name"))
                .authorities(rows.stream().map(Authority::fromRow).collect(Collectors.toSet()))
                .build());
    }
}
