package com.selling.system.auth.users.manager.controller.impl;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.users.manager.controller.api.UsersApi;
import com.selling.system.auth.users.manager.service.api.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Mono<ResponseEntity<UsersDto>> getUsers() {
        return usersService.getAllUsers()
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UserDto>> getUser(String username) {
        return usersService.getUser(username)
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }
}
