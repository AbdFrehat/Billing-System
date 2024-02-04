package com.selling.system.auth.users.manager.service.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.UserDeleteRequest;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.request.user.UserUpdateInfoRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.auth.users.manager.service.api.UsersService;
import com.selling.system.shared.module.exceptions.client.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.User.EMAIL;
import static com.selling.system.auth.shared.module.constants.Columns.User.USERNAME;

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

    @Override
    public Mono<UpdatedRowsResponse> updateUserInfo(UserUpdateInfoRequest request) {
        return usersRepository.updateUserInfo(request)
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<NameExistenceResponse> isUsernameExists(String username) {
        return usersRepository.isUsernameExists(username)
                .map($ -> NameExistenceResponse.builder().exists($).build());
    }

    @Override
    public Mono<NameExistenceResponse> isEmailExists(String email) {
        return usersRepository.isEmailExists(email)
                .map($ -> NameExistenceResponse.builder().exists($).build());
    }

    public Mono<NameExistenceResponse> isFieldValueExists(String fieldName, String value) {
        Mono<NameExistenceResponse> fieldExists;
        if (EMAIL.equals(fieldName)) {
            fieldExists = this.isEmailExists(value);
        } else if (USERNAME.equals(fieldName)) {
            fieldExists = this.isUsernameExists(value);
        } else {
            throw new BadRequestException("The fieldName is unknown", HttpStatus.BAD_REQUEST.value());
        }
        return fieldExists;
    }
}
