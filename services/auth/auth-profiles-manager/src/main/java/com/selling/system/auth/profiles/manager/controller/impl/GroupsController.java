package com.selling.system.auth.profiles.manager.controller.impl;

import com.selling.system.auth.profiles.manager.controller.api.GroupsApi;
import com.selling.system.auth.profiles.manager.service.api.GroupsService;
import com.selling.system.auth.shared.module.models.dto.GroupsDto;
import com.selling.system.auth.shared.module.models.request.group.GroupDeleteRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupInsertRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GroupsController implements GroupsApi {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }


    @Override
    public Mono<ResponseEntity<GroupsDto>> getGroups() {
        return groupsService.getAllGroups()
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateGroupName(GroupUpdateNameRequest request) {
        return groupsService.updateGroupName(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> insertGroup(GroupInsertRequest request) {
        return groupsService.insertGroup(request)
                .map($ -> ResponseEntity.status(HttpStatus.CREATED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteGroup(GroupDeleteRequest request) {
        return groupsService.deleteGroup(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }
}
