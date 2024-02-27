package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.GroupsDto;
import com.selling.system.auth.shared.module.models.request.group.GroupDeleteRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupInsertRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface GroupsService {

    Mono<GroupsDto> getAllGroups();

    Mono<UpdatedRowsResponse> updateGroupName(GroupUpdateNameRequest request);

    Mono<UpdatedRowsResponse> insertGroup(GroupInsertRequest request);

    Mono<UpdatedRowsResponse> deleteGroup(GroupDeleteRequest request);
}
