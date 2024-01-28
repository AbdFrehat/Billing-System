package com.selling.system.auth.shared.module.repository.impl;

import com.selling.system.auth.shared.module.mapper.api.GroupMapper;
import com.selling.system.auth.shared.module.models.entities.Group;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import com.selling.system.auth.shared.module.repository.api.GroupsRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.Group.GROUP_NAME;
import static com.selling.system.auth.shared.module.models.enums.Query.*;

@Repository
public class GroupsRepositoryImpl implements GroupsRepository {

    private final DatabaseClient client;

    private final QueryProvider provider;

    public GroupsRepositoryImpl(DatabaseClient client, QueryProvider provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public Flux<Group> retrieveAllGroups() {
        return client.sql(provider.provide(RETRIEVE_ALL_GROUPS))
                .fetch()
                .all()
                .flatMap(GroupMapper::fromRow);
    }

    @Override
    public Mono<Long> updateGroupName(String groupName, String updatedGroupName) {
        return client.sql(provider.provide(UPDATE_GROUP_NAME))
                .bind(GROUP_NAME, groupName)
                .bind("update_group_name", updatedGroupName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> saveGroup(String groupName) {
        return client.sql(provider.provide(ADD_GROUP))
                .bind(GROUP_NAME, groupName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> deleteProfilesAuthoritiesGroup(String groupName) {
        return client.sql(provider.provide(DELETE_PROFILE_AUTHORITIES_GROUP))
                .bind(GROUP_NAME, groupName)
                .fetch()
                .rowsUpdated();
    }

    @Override
    public Mono<Long> deleteAuthoritiesGroup(String groupName, Long count) {
        return client.sql(provider.provide(DELETE_AUTHORITIES_GROUP))
                .bind(GROUP_NAME, groupName)
                .fetch()
                .rowsUpdated()
                .map($ -> $ + count);
    }

    @Override
    public Mono<Long> deleteGroup(String groupName, Long count) {
        return client.sql(provider.provide(DELETE_GROUP))
                .bind(GROUP_NAME, groupName)
                .fetch()
                .rowsUpdated()
                .map($ -> $ + count);
    }
}
