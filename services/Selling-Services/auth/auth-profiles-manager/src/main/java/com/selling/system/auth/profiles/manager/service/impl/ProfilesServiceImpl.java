package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.ProfilesService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.profile.ProfileDeleteRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.ProfilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.models.enums.Query.ADD_PROFILE_AUTHORITIES;
import static com.selling.system.auth.shared.module.models.enums.Query.DELETE_PROFILE_AUTHORITIES;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    private final Mapper mapper;
    private final ProfilesRepository profilesRepository;

    public ProfilesServiceImpl(Mapper mapper, ProfilesRepository profilesRepository) {
        this.mapper = mapper;
        this.profilesRepository = profilesRepository;
    }

    @Override
    @Transactional
    public Mono<UpdatedRowsResponse> deleteProfileByName(ProfileDeleteRequest request) {
        return profilesRepository.deleteProfileAuthorities(request.getProfileName())
                .flatMap(($) -> profilesRepository.deleteProfileByName(request.getProfileName(), $))
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    @Transactional
    public Mono<UpdatedRowsResponse> saveProfile(ProfileInsertRequest profileInsertRequest) {
        return profilesRepository.saveProfile(profileInsertRequest.getProfileName())
                .flatMap($ -> profilesRepository.modifyProfileAuthorities(profileInsertRequest.getProfileName(),
                        profileInsertRequest.getAuthorities(), $, ADD_PROFILE_AUTHORITIES))
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        return profilesRepository.updateProfileName(profileUpdateRequest.getProfileName(), profileUpdateRequest.getUpdatedProfileName())
                .flatMap($ -> profilesRepository.modifyProfileAuthorities(profileUpdateRequest.getProfileName(), profileUpdateRequest.getAddedAuthorities(),
                        $, ADD_PROFILE_AUTHORITIES))
                .flatMap($ -> profilesRepository.modifyProfileAuthorities(profileUpdateRequest.getProfileName(), profileUpdateRequest.getRemovedAuthorities(),
                        $, DELETE_PROFILE_AUTHORITIES))
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<ProfilesDto> getProfiles() {
        return profilesRepository.retrieveAllProfiles()
                .map(mapper::profileToProfileDto)
                .collectList()
                .map(mapper::profilesToProfilesDto);
    }

    @Override
    public Mono<ProfileDto> getProfileByName(String profileName) {
        return profilesRepository.retrieveProfileByName(profileName)
                .map(mapper::profileToProfileDto);
    }

    @Override
    public Mono<ProfileNameExistenceResponse> isProfileExist(String profileName) {
        return profilesRepository.isProfileExist(profileName);
    }

}
