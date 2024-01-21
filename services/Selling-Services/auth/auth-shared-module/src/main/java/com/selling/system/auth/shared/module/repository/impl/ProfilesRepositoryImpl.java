package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.builder.api.QueryBuilder;
import com.selling.system.auth.shared.module.mapper.api.ProfileMapper;
import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.request.ProfileRequestInsert;
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

import static com.selling.system.auth.shared.module.models.enums.Query.*;

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
        return client.sql(provider.provider(RETRIEVE_ALL_PROFILES))
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("profile_id"))
                .flatMap(ProfileMapper::fromRows);
    }

    @Override
    public Mono<Profile> retrieveProfileByName(String profileName) {
        return client.sql(provider.provider(RETRIEVE_PROFILE))
                .bind("profile_name", profileName)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("profile_id"))
                .flatMap(ProfileMapper::fromRows)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new ProfileNotFoundException()));
    }

    @Override
    public Mono<ProfileNameExistenceResponse> isProfileExist(String profileName) {
        return client.sql(provider.provider(IS_PROFILE_NAME_EXISTS))
                .bind("profile_name", profileName)
                .fetch()
                .first()
                .flatMap(ProfileMapper::fromCountQueryRow);
    }

    @Override
    public Mono<Long> deleteProfileByName(String profileName, Long count) {
        return client.sql(provider.provider(DELETE_PROFILE))
                .bind("profile_name", profileName)
                .fetch()
                .rowsUpdated()
                .map(updatedRows -> updatedRows + count);
    }

    @Override
    public Mono<Long> deleteProfileAuthorities(String profileName) {
        return client.sql(provider.provider(DELETE_ALL_PROFILE_AUTHORITIES))
                .bind("profile_name", profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveProfile(String profileName) {
        return client.sql(provider.provider(ADD_PROFILE))
                .bind("profile_name", profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveProfileAuthorities(ProfileRequestInsert profileRequestInsert, Long count) {
        return builder.buildInsertProfileAuthoritiesQuery(provider.provider(ADD_PROFILE_AUTHORITIES), profileRequestInsert.getAuthorities())
                .flatMap(query -> {
                    DatabaseClient.GenericExecuteSpec genericExecuteSpec = client.sql(query)
                            .bind("profile_name", profileRequestInsert.getProfileName());
                    Iterator<String> iterator = profileRequestInsert.getAuthorities().iterator();
                    int counter = 1;
                    while (iterator.hasNext()) {
                        genericExecuteSpec = genericExecuteSpec.bind("authority_name_" + counter++, iterator.next());
                    }
                    return genericExecuteSpec.fetch().rowsUpdated();
                })
                .map(updatedRows -> updatedRows + count)
                .onErrorReturn(AuthoritiesEmptyException.class, count);
    }

}
