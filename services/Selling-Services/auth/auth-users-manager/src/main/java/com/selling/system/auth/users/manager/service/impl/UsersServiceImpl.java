package com.selling.system.auth.users.manager.service.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.auth.users.manager.service.api.UsersService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final Mapper mapper;

    public UsersServiceImpl(UsersRepository usersRepository, Mapper mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<UsersDto> getAllUsers() {
        return usersRepository.retrieveAllUsers()
                .map(mapper::userToUserDto)
                .collectList()
                .map(mapper::usersToUsersDto);
    }

    @Override
    public Mono<UserDto> getUser(String username) {
        return usersRepository.retrieveUserByName(username)
                .map(mapper::userToUserDto);
    }
}
