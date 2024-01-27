package com.selling.system.auth.shared.module.mapper.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.AuthorityDto;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.selling.system.shared.module.utils.CollectionUtil.isEmpty;

@Component
public class AuthMapper implements Mapper {

    @Override
    public ProfileDto profileToProfileDto(Profile profile) {
        return ProfileDto.builder().profileName(profile.getProfileName())
                .authorities(authoritiesToAuthoritiesDto(profile.getAuthorities()))
                .build();
    }

    @Override
    public ProfilesDto profilesToProfilesDto(List<ProfileDto> profiles) {
        return ProfilesDto.builder().profilesDto(profiles).build();
    }

    @Override
    public AuthorityDto authorityToAuthorityDto(Authority authority) {
        return AuthorityDto.builder()
                .authorityName(authority.getAuthorityName())
                .groupName(authority.getGroupName())
                .build();
    }

    @Override
    public Map<String, Set<AuthorityDto>> authoritiesToAuthoritiesDto(Set<Authority> authorities) {
        if (isEmpty(authorities) || authorities.contains(null)) return Map.of();
        return authorities.stream()
                .map(this::authorityToAuthorityDto)
                .collect(Collectors.groupingBy(AuthorityDto::getGroupName, Collectors.toSet()));
    }


}
