package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.UserMapper;
import com.selling.system.auth.shared.module.models.entities.User;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.shared.module.exceptions.business.UserNotFoundException;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                .bind(USER_NAME, username)
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
                .bind(USER_NAME, username)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveUser(UserInsertRequest request) {
        return client.sql(provider.provide(ADD_USER))
                .bind(USER_NAME, request.getUsername())
                .bind(EMAIL, request.getEmail())
                .bind(PASSWORD, request.getPassword())
                .bind(PHONE, request.getPhone())
                .bind(PROFILE_NAME, request.getProfileName())
                .bind(COUNTRY, request.getCountry())
                .bind(CITY, request.getCity())
                .bind(STREET, request.getStreet())
                .fetch()
                .rowsUpdated();
    }
}
