package com.selling.system.auth.shared.module.mapper.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.AuthorityDto;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthMapper implements Mapper {
    @Override
    public ProfileDto profileToProfileDto(Profile profile) {
        return ProfileDto.builder()
                .profileName(profile.getProfileName())
                .authorities(profile.getAuthorities().stream().map(this::authorityToAuthorityDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProfilesDto profilesToProfilesDto(List<Profile> profiles) {
        return ProfilesDto.builder()
                .profilesDto(profiles.stream()
                        .map(this::profileToProfileDto)
                        .collect(Collectors.toList())).build();
    }

    @Override
    public AuthorityDto authorityToAuthorityDto(Authority authority) {
        return AuthorityDto.builder()
                .authorityName(authority.getAuthorityName())
                .build();
    }

    @Override
    public List<AuthorityDto> authoritiesToAuthoritiesDto(List<Authority> authorities) {
        return authorities.stream()
                .map(this::authorityToAuthorityDto)
                .collect(Collectors.toList());
    }
}
