package com.selling.system.auth.shared.module.provider.impl;

import com.selling.system.auth.shared.module.provider.api.QueryProvider;
import org.springframework.stereotype.Component;

@Component
public class QueryProviderImpl implements QueryProvider {

    @Override
    public String provider(String queryName) {
        if (queryName.equals("RETRIEVE_ALL_PROFILES")) {
            return """
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
        }
        return null;
    }
}
