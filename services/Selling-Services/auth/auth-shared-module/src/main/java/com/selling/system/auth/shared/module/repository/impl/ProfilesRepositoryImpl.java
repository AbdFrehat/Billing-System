package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.ProfilesRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class ProfilesRepositoryImpl implements ProfilesRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    public ProfilesRepositoryImpl(DatabaseClient client, QueryProvider provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public Flux<Profile> retrieveAllProfiles() {
        return client.sql(provider.provider("RETRIEVE_ALL_PROFILES"))
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("profile_id"))
                .flatMap(Profile::fromRows);
    }


}
