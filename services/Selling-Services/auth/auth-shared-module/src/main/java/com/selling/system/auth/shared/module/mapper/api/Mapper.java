package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.dto.*;
import com.selling.system.auth.shared.module.models.entities.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Columns.Fields.COUNT;


@Component
public interface Mapper {


    ProfileDto profileToProfileDto(Profile profile);

    ProfilesDto profilesToProfilesDto(List<ProfileDto> profiles);

    ClientDto clientToClientDto(Client client);

    ClientsDto clientsToClientsDto(List<ClientDto> clients);

    AuthorityDto authorityToAuthorityDto(Authority authority);

    Map<String, Set<AuthorityDto>> authoritiesToAuthoritiesDto(Set<Authority> authorities);

    GroupDto groupToGroupDto(Group group);

    GroupsDto groupsToGroupsDto(List<GroupDto> groupsDto);

    UserDto userToUserDto(User user);

    UsersDto usersToUsersDto(List<UserDto> users);

    static Mono<Boolean> fromCountQueryRow(Map<String, Object> row) {
        return Mono.just(((long) row.get(COUNT)) > 0L);
    }

}
