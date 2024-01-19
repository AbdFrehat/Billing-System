package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.ProfileModifierService;
import com.selling.system.auth.shared.module.repository.api.AuthoritiesRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileModifierServiceImpl implements ProfileModifierService {

    private final AuthoritiesRepository authoritiesRepository;

    public ProfileModifierServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

//    @Override
//    public Profile modifyProfile(ProfileUpdateRequest profileUpdateRequest, Profile profile) {
//        var authorities = profile.getAuthorities();
//        var allAuthorities = authoritiesRepository.findAll();
//        if (isNotEmpty(profileUpdateRequest.getUpdatedName())) {
//            profile.setProfileName(profileUpdateRequest.getUpdatedName());
//        }
//        if (profileUpdateRequest.getAddedAuthorities() != null && !profileUpdateRequest.getAddedAuthorities().isEmpty()) {
//            authorities.addAll(allAuthorities.stream().filter(authority -> profileUpdateRequest.getAddedAuthorities().contains(authority.getAuthorityName())).toList());
//        }
//        if (profileUpdateRequest.getRemovedAuthorities() != null && !profileUpdateRequest.getRemovedAuthorities().isEmpty()) {
//            profileUpdateRequest.getRemovedAuthorities().stream().map(authorityName -> Authority.builder().authorityName(authorityName).build()).toList().forEach(authorities::remove);
//        }
//        profile.setAuthorities(authorities);
//        return profile;
//    }
}
