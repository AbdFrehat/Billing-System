package com.orderizer.data.sync.orders.config;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ReactiveElasticsearchConfiguration {

    private final LocalAppConfig localAppConfig;

    @NotNull
    @Override
    public ClientConfiguration clientConfiguration() {
        var elasticsearch = localAppConfig.getElasticsearch();
        if (elasticsearch.isSecurityEnabled()) {
            return ClientConfiguration.builder()
                    .connectedTo(elasticsearch.getUri())
                    .usingSsl(elasticsearch.getCaFingerprint())
                    .withBasicAuth(elasticsearch.getUsername(), elasticsearch.getPassword())
                    .build();
        }
        return ClientConfiguration.builder()
                .connectedTo(elasticsearch.getUri())
                .build();
    }

}
