package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.GroupsDto;
import com.selling.system.auth.shared.module.models.request.group.GroupInsertRequest;
import com.selling.system.auth.shared.module.models.request.group.GroupUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/groups")
public interface GroupsApi {
    @GetMapping
    Mono<ResponseEntity<GroupsDto>> getGroups();

    @PutMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> updateGroupName(@RequestBody @Valid GroupUpdateNameRequest request);
    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> insertGroup(@RequestBody @Valid GroupInsertRequest request);
}
