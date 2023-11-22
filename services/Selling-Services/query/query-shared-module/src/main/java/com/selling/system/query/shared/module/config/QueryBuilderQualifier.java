package com.selling.system.query.shared.module.config;

import com.selling.system.query.shared.module.service.QueryBuilderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryBuilderQualifier {

    private final QueryBuilderService queryFreeBuilderService;
    private final QueryBuilderService queryOptBuilderService;

    private final QueryBuilderService queryBuilderService;

    @Value("${config.query.builder}")
    private String queryImplementation;

    public QueryBuilderQualifier(@Qualifier("query-free-builder") QueryBuilderService queryFreeBuilderService,
                                 @Qualifier("query-opt-builder") QueryBuilderService queryOptBuilderService,
                                 @Qualifier("query-builder") QueryBuilderService queryBuilderService) {
        this.queryFreeBuilderService = queryFreeBuilderService;
        this.queryOptBuilderService = queryOptBuilderService;
        this.queryBuilderService = queryBuilderService;
    }

    @Bean(name = "queryBuilderService")
    public QueryBuilderService qualifyBuilderQueryBean() {
        if ("query-free-builder".equals(queryImplementation))
            return queryFreeBuilderService;
        if ("query-opt-builder".equals(queryImplementation))
            return queryOptBuilderService;
        if ("query-builder".equals(queryImplementation))
            return queryBuilderService;
        throw new IllegalArgumentException("invalid or empty config.query.builder filed");

    }

}
