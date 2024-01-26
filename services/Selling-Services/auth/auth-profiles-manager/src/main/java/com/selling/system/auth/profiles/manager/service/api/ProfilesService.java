package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface ProfilesService {

    Mono<ProfilesDto> getProfiles();

    Mono<ProfileDto> getProfileByName(String profileName);

    Mono<ProfileNameExistenceResponse> isProfileExist(String profileName);

    Mono<UpdatedRowsResponse> deleteProfileByName(String profileName);

    Mono<UpdatedRowsResponse> saveProfile(ProfileInsertRequest profileInsertRequest);


}
