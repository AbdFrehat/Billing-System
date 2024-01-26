package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.request.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProfilesRepository {

    Flux<Profile> retrieveAllProfiles();

    Mono<Profile> retrieveProfileByName(String profileName);

    Mono<ProfileNameExistenceResponse> isProfileExist(String profileName);

    Mono<Long> deleteProfileByName(String profileName, Long count);

    Mono<Long> deleteProfileAuthorities(String profileName);

    Mono<Long> saveProfile(String profileName);

    Mono<Long> saveProfileAuthorities(ProfileInsertRequest profileInsertRequest, Long count);
}
