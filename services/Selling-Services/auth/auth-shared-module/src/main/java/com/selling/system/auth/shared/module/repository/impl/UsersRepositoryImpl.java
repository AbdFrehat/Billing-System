package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.UserMapper;
import com.selling.system.auth.shared.module.models.entities.User;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.UsersRepository;
import com.selling.system.shared.module.exceptions.business.UserNotFoundException;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.User.USER_ID;
import static com.selling.system.auth.shared.module.constants.Columns.User.USER_NAME;
import static com.selling.system.auth.shared.module.models.enums.Query.RETRIEVE_ALL_USERS;
import static com.selling.system.auth.shared.module.models.enums.Query.RETRIEVE_USER;

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
}
