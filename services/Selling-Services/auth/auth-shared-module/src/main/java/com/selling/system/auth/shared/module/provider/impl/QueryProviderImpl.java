package com.selling.system.auth.shared.module.provider.impl;

import com.selling.system.auth.shared.module.models.enums.Query;
import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import org.springframework.stereotype.Component;

@Component
public class QueryProviderImpl implements QueryProvider {

    @Override
    public String provider(Query query) {
        return switch (query) {
            case RETRIEVE_ALL_PROFILES -> """
                    SELECT
                        p.profile_name,
                        p.profile_id,
                        a.authority_name,
                        a.authority_id
                    FROM
                        profiles p
                    INNER JOIN
                        profiles_authorities pa
                    ON
                        p.profile_id = pa.profile_id
                    INNER JOIN
                        authorities a
                    ON
                        pa.authority_id = a.authority_id
                                        """;
            case RETRIEVE_PROFILE -> """
                    SELECT
                        p.profile_name,
                        p.profile_id,
                        a.authority_name,
                        a.authority_id
                    FROM
                        profiles p
                    INNER JOIN
                        profiles_authorities pa
                    ON
                        p.profile_id = pa.profile_id
                    INNER JOIN
                        authorities a
                    ON
                        pa.authority_id = a.authority_id
                    WHERE
                        p.profile_name = :profile_name;
                    """;
            case ADD_PROFILE -> """
                    INSERT INTO profiles (profile_name)
                    VALUES
                        (:profile_name);
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
                         authority_id = (
                            SELECT
                                authorities.authority_id
                            FROM
                                authorities
                            WHERE
                                authority_name = :authority_name
                         );
                    """;
            case ADD_PROFILE_AUTHORITIES -> """
                    INSERT INTO profiles_authorities (profile_id, authority_id)
                    """;
            case RETRIEVE_PROFILE_AUTHORITIES_KEYS -> """
                    SELECT
                        (SELECT profile_id FROM profiles WHERE profile_name = %s),
                        (SELECT authority_id FROM authorities WHERE authority_name = %s)
                    """;
        };
    }
}
