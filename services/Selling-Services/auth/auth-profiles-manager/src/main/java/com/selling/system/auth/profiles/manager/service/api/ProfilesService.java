package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.request.ProfileUpdateRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfilesService {

//    Mono<ProfileDto> createProfile(Profile profile);
//
//    Mono<ProfileDto> updateProfile(ProfileUpdateRequest profileUpdateRequest);
//
//    void deleteProfile(int id);

    Mono<ProfilesDto> getProfiles();

}
