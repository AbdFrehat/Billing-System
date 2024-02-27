package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.profile.ProfileDeleteRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface ProfilesService {

    Mono<ProfilesDto> getProfiles();

    Mono<ProfileDto> getProfileByName(String profileName);

    Mono<NameExistenceResponse> isProfileExist(String profileName);

    Mono<UpdatedRowsResponse> deleteProfileByName(ProfileDeleteRequest request);

    Mono<UpdatedRowsResponse> saveProfile(ProfileInsertRequest profileInsertRequest);

    Mono<UpdatedRowsResponse> updateProfile(ProfileUpdateRequest profileUpdateRequest);


}
