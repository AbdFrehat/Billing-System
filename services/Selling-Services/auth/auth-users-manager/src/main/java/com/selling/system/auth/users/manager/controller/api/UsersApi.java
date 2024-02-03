package com.selling.system.auth.users.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.UserDeleteRequest;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface UsersApi {

    @GetMapping
    Mono<ResponseEntity<UsersDto>> getUsers();
    @GetMapping("/{username}")
    Mono<ResponseEntity<UserDto>> getUser(@PathVariable("username") String username);

    @DeleteMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteUser(@RequestBody @Valid UserDeleteRequest request);
    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> saveUser(@RequestBody @Valid UserInsertRequest request);
}
