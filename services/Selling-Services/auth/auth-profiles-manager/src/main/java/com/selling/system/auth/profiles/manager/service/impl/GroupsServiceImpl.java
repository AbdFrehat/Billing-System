package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.GroupsService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.GroupsDto;
import com.selling.system.auth.shared.module.models.request.group.GroupInsertRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.GroupsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GroupsServiceImpl implements GroupsService {

    private final GroupsRepository groupsRepository;

    private final Mapper mapper;

    public GroupsServiceImpl(GroupsRepository groupsRepository, Mapper mapper) {
        this.groupsRepository = groupsRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<GroupsDto> getAllGroups() {
        return groupsRepository.retrieveAllGroups()
                .map(mapper::groupToGroupDto)
                .collectList()
                .map(mapper::groupsToGroupsDto);
    }

    @Override
    public Mono<UpdatedRowsResponse> updateGroupName(GroupUpdateNameRequest request) {
        return groupsRepository.updateGroupName(request.getGroupName(),
                        request.getUpdatedGroupName())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> insertGroup(GroupInsertRequest request) {
        return groupsRepository.saveGroup(request.getGroupName())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }
}
