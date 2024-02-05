package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.mapper.api.UserMapper;
import com.selling.system.auth.shared.module.models.entities.User;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.request.user.UserUpdateInfoRequest;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.shared.module.exceptions.business.UserNotFoundException;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.Bind.UPDATED_USERNAME;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;
import static com.selling.system.auth.shared.module.constants.Columns.User.*;
import static com.selling.system.auth.shared.module.models.enums.Query.*;

@RestController
public class UsersRepositoryImpl implements UsersRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    public UsersRepositoryImpl(DatabaseClient client, QueryProvider provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public Flux<User> retrieveAllUsers() {
        return client.sql(provider.provide(RETRIEVE_ALL_USERS))
                .fetch()
                .all()
                .bufferUntilChanged($ -> $.get(USER_ID))
                .flatMap(UserMapper::fromRows);
    }

    @Override
    public Mono<User> retrieveUserByName(String username) {
        return client.sql(provider.provide(RETRIEVE_USER))
                .bind(USERNAME, username)
                .fetch()
                .all()
                .bufferUntilChanged($ -> $.get(USER_ID))
                .flatMap(UserMapper::fromRows)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    @Override
    public Mono<Long> deleteUser(String username) {
        return client.sql(provider.provide(DELETE_USER))
                .bind(USERNAME, username)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveUser(UserInsertRequest request) {
        return client.sql(provider.provide(ADD_USER))
                .bind(USERNAME, request.getUsername())
                .bind(EMAIL, request.getEmail())
                .bind(PASSWORD, request.getPassword())
                .bind(PHONE, request.getPhone())
                .bind(COUNTRY, request.getCountry())
                .bind(CITY, request.getCity())
                .bind(STREET, request.getStreet())
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> updateUserInfo(UserUpdateInfoRequest request) {
        return client.sql(provider.provide(UPDATE_USER_INFO))
                .bind(UPDATED_USERNAME, request.getUpdatedUsername())
                .bind(EMAIL, request.getEmail())
                .bind(PHONE, request.getPhone())
                .bind(COUNTRY, request.getCountry())
                .bind(CITY, request.getCity())
                .bind(STREET, request.getStreet())
                .bind(USERNAME, request.getUsername())
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> updateUserPassword(String username, String password) {
        return client.sql(provider.provide(UPDATE_USER_PASSWORD))
                .bind(USERNAME, username)
                .bind(PASSWORD, password)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> updateUserProfile(String username, String profileName) {
        return client.sql(provider.provide(ASSIGN_USER_PROFILE))
                .bind(USERNAME, username)
                .bind(PROFILE_NAME, profileName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> enableUser(String username, boolean flag) {
        return client.sql(provider.provide(ENABLE_USER))
                .bind(USERNAME, username)
                .bind(IS_ENABLED, flag)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> lockUser(String username, boolean flag) {
        return client.sql(provider.provide(LOCK_USER))
                .bind(USERNAME, username)
                .bind(IS_ACCOUNT_LOCKED, flag)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> expireCredentialUser(String username, boolean flag) {
        return client.sql(provider.provide(EXPIRE_USER_CREDENTIAL))
                .bind(USERNAME, username)
                .bind(IS_CREDENTIAL_EXPIRED, flag)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> expireAccountUser(String username, boolean flag) {
        return client.sql(provider.provide(EXPIRE_USER_ACCOUNT))
                .bind(USERNAME, username)
                .bind(IS_ACCOUNT_EXPIRED, flag)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Boolean> isEmailExists(String email) {
        return client.sql(provider.provide(IS_EMAIL_EXISTS))
                .bind(EMAIL, email)
                .fetch()
                .first()
                .flatMap(Mapper::fromCountQueryRow);
    }

    @Override
    public Mono<Boolean> isUsernameExists(String username) {
        return client.sql(provider.provide(IS_USERNAME_EXISTS))
                .bind(USERNAME, username)
                .fetch()
                .first()
                .flatMap(Mapper::fromCountQueryRow);
    }
}
