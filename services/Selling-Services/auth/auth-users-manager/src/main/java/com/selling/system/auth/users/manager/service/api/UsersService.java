package com.selling.system.auth.users.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import reactor.core.publisher.Mono;

public interface UsersService {

    Mono<UsersDto> getAllUsers();
    Mono<UserDto> getUser(String username);
}
