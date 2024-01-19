package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.ProfileModifierService;
import com.selling.system.auth.profiles.manager.service.api.ProfilesService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.repository.api.ProfilesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    private final Mapper mapper;
    private final ProfilesRepository profilesRepository;
    private final ProfileModifierService profileModifierService;

    public ProfilesServiceImpl(Mapper mapper, ProfilesRepository profilesRepository, ProfileModifierService profileModifierService) {
        this.mapper = mapper;
        this.profilesRepository = profilesRepository;
        this.profileModifierService = profileModifierService;
    }
//
//    @Override
//    public Mono<ProfileDto> createProfile(Profile profile) {
//        return Mono.just(mapper.profileToProfileDto(profilesRepository.save(profile)));
//    }
//
//    @Override
//    public Mono<ProfileDto> updateProfile(ProfileUpdateRequest profileUpdateRequest) {
//        var optProfile = profilesRepository.findProfileByProfileName(profileUpdateRequest.getName());
//        if (optProfile.isPresent()) {
//            var profile = profileModifierService.modifyProfile(profileUpdateRequest, optProfile.get());
//            return Mono.just(mapper.profileToProfileDto(profilesRepository.save(profile)));
//        } else {
//            throw new ProfileNotFoundException();
//        }
//    }
//
//    @Override
//    public void deleteProfile(int id) {
//        profilesRepository.deleteById(id);
//    }

    @Override
    public Mono<ProfilesDto> getProfiles() {
        return profilesRepository.retrieveAllProfiles()
                .map(mapper::profileToProfileDto)
                .collectList()
                .map(mapper::profilesToProfilesDto);
    }

}
