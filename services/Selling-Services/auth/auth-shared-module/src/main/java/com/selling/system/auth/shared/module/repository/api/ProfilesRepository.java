package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.Profile;
import reactor.core.publisher.Flux;

public interface ProfilesRepository {

    Flux<Profile> retrieveAllProfiles();
}
