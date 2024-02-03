package com.selling.system.auth.users.manager.service.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.UserDeleteRequest;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.auth.users.manager.service.api.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final Mapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public Mono<UpdatedRowsResponse> deleteUser(UserDeleteRequest request) {
        return usersRepository.deleteUser(request.getUsername())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> saveUser(UserInsertRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return usersRepository.saveUser(request)
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }
}
