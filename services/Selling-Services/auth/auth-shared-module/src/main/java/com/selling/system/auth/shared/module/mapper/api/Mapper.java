package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.dto.AuthorityDto;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface Mapper {


    ProfileDto profileToProfileDto(Profile profile);

    ProfilesDto profilesToProfilesDto(List<Profile> profiles);

    AuthorityDto authorityToAuthorityDto(Authority authority);

    List<AuthorityDto> authoritiesToAuthoritiesDto(List<Authority> authorities);

}
