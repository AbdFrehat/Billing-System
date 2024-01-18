package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.ProfileModifierService;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.request.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.repository.AuthoritiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.selling.system.shared.module.utils.StringUtil.isNotEmpty;

@Service
public class ProfileModifierServiceImpl implements ProfileModifierService {

    private final AuthoritiesRepository authoritiesRepository;

    public ProfileModifierServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public Profile modifyProfile(ProfileUpdateRequest profileUpdateRequest, Profile profile) {
        Set<Authority> authorities = profile.getAuthorities();
        List<Authority> allAuthorities = authoritiesRepository.findAll();
        if (isNotEmpty(profileUpdateRequest.getUpdatedName())) {
            profile.setProfileName(profileUpdateRequest.getUpdatedName());
        }
        if (profileUpdateRequest.getAddedAuthorities() != null && !profileUpdateRequest.getAddedAuthorities().isEmpty()) {
            authorities.addAll(allAuthorities.stream().filter(authority -> profileUpdateRequest.getAddedAuthorities().contains(authority.getAuthorityName())).toList());
        }
        if (profileUpdateRequest.getRemovedAuthorities() != null && !profileUpdateRequest.getRemovedAuthorities().isEmpty()) {
            profileUpdateRequest.getRemovedAuthorities().stream().map(authorityName -> Authority.builder().authorityName(authorityName).build()).toList().forEach(authorities::remove);
        }
        profile.setAuthorities(authorities);
        return profile;
    }
}
