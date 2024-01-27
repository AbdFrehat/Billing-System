package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.dto.AuthorityDto;
import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.entities.Authority;
import com.selling.system.auth.shared.module.models.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public interface Mapper {


    ProfileDto profileToProfileDto(Profile profile);

    ProfilesDto profilesToProfilesDto(List<ProfileDto> profiles);

    AuthorityDto authorityToAuthorityDto(Authority authority);

    Map<String, Set<AuthorityDto>> authoritiesToAuthoritiesDto(Set<Authority> authorities);

}
