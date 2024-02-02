package com.selling.system.auth.users.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface UsersApi {

    @GetMapping
    Mono<ResponseEntity<UsersDto>> getUsers();
    @GetMapping("/{username}")
    Mono<ResponseEntity<UserDto>> getUser(@PathVariable("username") String username);
}
