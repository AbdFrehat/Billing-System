package com.selling.system.auth.shared.module.mapper.api;

import com.selling.system.auth.shared.module.models.entities.Client;
import com.selling.system.auth.shared.module.models.entities.GrantType;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.selling.system.auth.shared.module.constants.Columns.Client.*;

public interface ClientMapper {

    static Mono<Client> fromRows(List<Map<String, Object>> rows) {
        return GrantTypeMapper.fromRow(rows.get(0))
                .flatMap($ -> ProfileMapper.fromRows(rows)
                        .map($$ -> Client.builder()
                                .clientSeq((int) rows.get(0).get(CLIENT_SEQ))
                                .clientName((String) rows.get(0).get(CLIENT_NAME))
                                .clientId((String) rows.get(0).get(CLIENT_ID))
                                .clientSecret((String) rows.get(0).get(CLIENT_SECRET))
                                .grantType(GrantType.builder()
                                        .grantId($.getGrantId())
                                        .grantTypes($.getGrantTypes())
                                        .build())
                                .profile($$)
                                .build()));
    }
}
