package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.User;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.User.*;

public interface UserMapper {

    static Mono<User> fromRows(List<Map<String, Object>> rows) {
        return ProfileMapper.fromRows(rows).map($ -> User.builder()
                .userId((int) rows.get(0).get(USER_ID))
                .username((String) rows.get(0).get(USER_NAME))
                .email((String) rows.get(0).get(EMAIL))
                .password((String) rows.get(0).get(PASSWORD))
                .phone((String) rows.get(0).get(PHONE))
                .createdAt((OffsetDateTime) rows.get(0).get(CREATED_AT))
                .enabled((boolean) rows.get(0).get(IS_ENABLED))
                .accountExpired((boolean) rows.get(0).get(IS_ACCOUNT_EXPIRED))
                .credentialExpired((boolean) rows.get(0).get(IS_CREDENTIAL_EXPIRED))
                .locked((boolean) rows.get(0).get(IS_ACCOUNT_LOCKED))
                .lastPasswordChanged((OffsetDateTime) rows.get(0).get(LAST_PASSWORD_CHANGED))
                .country((String) rows.get(0).get(COUNTRY))
                .city((String) rows.get(0).get(CITY))
                .street((String) rows.get(0).get(STREET))
                .profile($)
                .build());
    }
}
