package com.selling.system.auth.shared.module.provider.impl;

import com.selling.system.auth.shared.module.models.enums.Query;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import org.springframework.stereotype.Component;

@Component
public class QueryProviderImpl implements QueryProvider {

    @Override
    public String provide(Query query) {
        return switch (query) {
            case RETRIEVE_ALL_PROFILES -> """
                    SELECT
                        p.profile_name,
                        p.profile_id,
                        a.authority_name,
                        a.authority_id,
                        g.group_id,
                        g.group_name
                    FROM
                        profiles p
                    LEFT OUTER JOIN
                        profiles_authorities pa
                    ON
                        p.profile_id = pa.profile_id
                    LEFT OUTER JOIN
                        authorities a
                    ON
                        pa.authority_id = a.authority_id
                    LEFT OUTER JOIN
                        groups g
                    ON
                        g.group_id = a.group_id
                                        """;
            case RETRIEVE_PROFILE -> """
                    SELECT
                        p.profile_name,
                        p.profile_id,
                        a.authority_name,
                        a.authority_id,
                        g.group_id,
                        g.group_name
                    FROM
                        profiles p
                    LEFT OUTER JOIN
                        profiles_authorities pa
                    ON
                        p.profile_id = pa.profile_id
                    LEFT OUTER JOIN
                        authorities a
                    ON
                        pa.authority_id = a.authority_id
                    LEFT OUTER JOIN
                        groups g
                    ON
                        g.group_id = a.group_id
                    WHERE
                        p.profile_name = :profile_name
                    ;
                    """;
            case RETRIEVE_ALL_AUTHORITIES -> """
                    SELECT
                        a.authority_id,
                        a.authority_name,
                        g.group_id,
                        g.group_name
                    FROM
                        authorities a
                    LEFT OUTER JOIN
                        groups g
                    ON
                        g.group_id = a.group_id
                    """;
            case RETRIEVE_ALL_GROUPS -> """
                    SELECT
                        group_id,
                        group_name
                    FROM
                        groups;
                    """;
            case ADD_PROFILE -> """
                    INSERT INTO profiles (profile_name)
                    VALUES
                        (:profile_name);
                    """;
            case ADD_AUTHORITY -> """
                    INSERT INTO authorities
                        (authority_name, group_id)
                    VALUES %s
                    """;
            case ADD_GROUP -> """
                    INSERT INTO groups
                        (group_name)
                    VALUES (:group_name)
                    """;
            case DELETE_PROFILE -> """
                    DELETE FROM
                        profiles
                    WHERE
                        profile_name = :profile_name;
                    """;
            case IS_PROFILE_NAME_EXISTS -> """
                        SELECT
                            count(*) as profile_count
                        FROM
                            profiles
                        WHERE
                                profile_name = :profile_name;
                    """;
            case DELETE_ALL_PROFILE_AUTHORITIES -> """
                    DELETE FROM
                        profiles_authorities
                    WHERE
                            profile_id = (
                            SELECT
                                profile_id
                            FROM
                                profiles
                            WHERE
                                    profile_name = :profile_name
                        );
                    """;
            case DELETE_PROFILE_AUTHORITIES -> """
                    DELETE FROM
                        profiles_authorities
                    WHERE
                        profile_id = (
                            SELECT
                                profile_id
                            FROM
                                profiles
                            WHERE
                                profile_name = :profile_name
                        )
                    AND
                        authority_id
                    IN
                        ( %s )
                    """;
            case ADD_PROFILE_AUTHORITIES -> """
                    INSERT INTO profiles_authorities (profile_id, authority_id)
                    """;
            case RETRIEVE_PROFILE_AUTHORITIES_KEYS -> """
                    SELECT
                        (SELECT profile_id FROM profiles WHERE profile_name = :profile_name),
                        (SELECT authority_id FROM authorities WHERE authority_name = %s)
                    """;
            case RETRIEVE_AUTHORITIES_KEYS -> """
                    (SELECT authority_id FROM authorities WHERE authority_name = %s)
                    """;
            case UPDATE_PROFILE_NAME -> """
                    UPDATE
                        profiles
                    SET
                        profile_name = :updated_profile_name
                    WHERE
                        profile_name = :profile_name
                    """;
            case UPDATE_GROUP_NAME -> """
                    UPDATE
                        groups
                    SET
                        group_name = :update_group_name
                    WHERE
                        group_name = :group_name
                    """;
            case UPDATE_AUTHORITY_NAME -> """
                    UPDATE
                        authorities
                    SET
                        authority_name = :update_authority_name
                    WHERE
                        authority_name = :authority_name
                    """;
            case AUTHORITY_VALUES -> """
                    (%s, (SELECT groups.group_id FROM groups WHERE group_name = %s))
                    """;
        };
    }
}
