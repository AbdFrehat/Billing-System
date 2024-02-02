package com.selling.system.auth.shared.module.mapper.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.*;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Group;
import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.entities.User;
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
                .groupName(authority.getGroup().getGroupName())
                .build();
    }

    @Override
    public Map<String, Set<AuthorityDto>> authoritiesToAuthoritiesDto(Set<Authority> authorities) {
        if (isEmpty(authorities) || authorities.contains(null)) return Map.of();
        return authorities.stream()
                .map(this::authorityToAuthorityDto)
                .collect(Collectors.groupingBy(AuthorityDto::getGroupName, Collectors.toSet()));
    }

    @Override
    public GroupDto groupToGroupDto(Group group) {
        return GroupDto.builder().name(group.getGroupName()).build();
    }

    @Override
    public GroupsDto groupsToGroupsDto(List<GroupDto> groupsDto) {
        return GroupsDto.builder().groups(groupsDto).build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .profile(profileToProfileDto(user.getProfile()))
                .createdAt(user.getCreatedAt())
                .enabled(user.isEnabled())
                .accountExpired(user.isAccountExpired())
                .credentialExpired(user.isCredentialExpired())
                .locked(user.isLocked())
                .lastPasswordChanged(user.getLastPasswordChanged())
                .country(user.getCountry())
                .city(user.getCity())
                .street(user.getStreet())
                .build();
    }

    @Override
    public UsersDto usersToUsersDto(List<UserDto> users) {
        return UsersDto.builder()
                .users(users)
                .build();
    }

}
