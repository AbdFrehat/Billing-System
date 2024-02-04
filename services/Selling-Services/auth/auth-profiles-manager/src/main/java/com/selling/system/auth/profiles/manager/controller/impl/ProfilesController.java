package com.selling.system.auth.profiles.manager.controller.impl;

import com.selling.system.auth.profiles.manager.controller.api.ProfilesApi;
import com.selling.system.auth.profiles.manager.service.api.ProfilesService;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.profile.ProfileDeleteRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ProfilesController implements ProfilesApi {

    private final ProfilesService profilesService;

    public ProfilesController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @Override
    public Mono<ResponseEntity<ProfilesDto>> getProfiles() {
        return profilesService.getProfiles()
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<ProfileDto>> getProfile(String profileName) {
        return profilesService.getProfileByName(profileName)
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteProfile(ProfileDeleteRequest request) {
        return profilesService.deleteProfileByName(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

    @Override
    public Mono<ResponseEntity<NameExistenceResponse>> isProfileExist(String profileName) {
        return profilesService.isProfileExist(profileName)
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> saveProfile(ProfileInsertRequest profileInsertRequest) {
        return profilesService.saveProfile(profileInsertRequest)
                .map($ -> ResponseEntity.status(HttpStatus.CREATED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        return profilesService.updateProfile(profileUpdateRequest)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

}
