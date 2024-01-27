package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.builder.api.QueryBuilder;
import com.selling.system.auth.shared.module.mapper.api.ProfileMapper;
import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.enums.Query;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.ProfilesRepository;
import com.selling.system.shared.module.exceptions.Technical.AuthoritiesEmptyException;
import com.selling.system.shared.module.exceptions.business.ProfileNotFoundException;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Columns.Authority.AUTHORITY_NAME;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;
import static com.selling.system.auth.shared.module.models.enums.Query.*;
import static com.selling.system.shared.module.utils.CollectionUtil.isEmpty;
import static com.selling.system.shared.module.utils.StringUtil.isEmpty;

@Repository
public class ProfilesRepositoryImpl implements ProfilesRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    private final QueryBuilder builder;

    public ProfilesRepositoryImpl(DatabaseClient client, QueryProvider provider, QueryBuilder builder) {
        this.client = client;
        this.provider = provider;
        this.builder = builder;
    }

    @Override
    public Flux<Profile> retrieveAllProfiles() {
        return client.sql(provider.provide(RETRIEVE_ALL_PROFILES))
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get(PROFILE_ID))
                .flatMap(ProfileMapper::fromRows);
    }

    @Override
    public Mono<Profile> retrieveProfileByName(String profileName) {
        return client.sql(provider.provide(RETRIEVE_PROFILE))
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get(PROFILE_ID))
                .flatMap(ProfileMapper::fromRows)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new ProfileNotFoundException()));
    }

    @Override
    public Mono<ProfileNameExistenceResponse> isProfileExist(String profileName) {
        return client.sql(provider.provide(IS_PROFILE_NAME_EXISTS))
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .first()
                .flatMap(ProfileMapper::fromCountQueryRow);
    }

    @Override
    public Mono<Long> deleteProfileByName(String profileName, Long count) {
        return client.sql(provider.provide(DELETE_PROFILE))
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .rowsUpdated()
                .map(updatedRows -> updatedRows + count);
    }

    @Override
    public Mono<Long> deleteProfileAuthorities(String profileName) {
        return client.sql(provider.provide(DELETE_ALL_PROFILE_AUTHORITIES))
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveProfile(String profileName) {
        return client.sql(provider.provide(ADD_PROFILE))
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> modifyProfileAuthorities(String profileName, Set<String> authorities, Long count, Query query) {
        if (isEmpty(authorities)) return Mono.just(count);
        Mono<String> builtQuery = builder.buildProfileAuthoritiesQuery(query, authorities);
        return builtQuery
                .flatMap($ -> {
                    DatabaseClient.GenericExecuteSpec genericExecuteSpec = client.sql($)
                            .bind(PROFILE_NAME, profileName);
                    Iterator<String> iterator = authorities.iterator();
                    int counter = 1;
                    while (iterator.hasNext()) {
                        genericExecuteSpec = genericExecuteSpec.bind(AUTHORITY_NAME + counter++, iterator.next());
                    }
                    return genericExecuteSpec.fetch().rowsUpdated();
                })
                .map(updatedRows -> updatedRows + count)
                .onErrorReturn(AuthoritiesEmptyException.class, count);
    }

    @Override
    public Mono<Long> updateProfileName(String profileName, String updatedProfileName) {
        if (isEmpty(updatedProfileName)) return Mono.just(0L);
        return client.sql(provider.provide(UPDATE_PROFILE_NAME))
                .bind(PROFILE_NAME, profileName)
                .bind("updated_profile_name", updatedProfileName)
                .fetch()
                .rowsUpdated();
    }

}
