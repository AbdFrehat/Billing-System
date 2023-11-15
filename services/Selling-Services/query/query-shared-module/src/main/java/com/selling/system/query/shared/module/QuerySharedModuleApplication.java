package com.selling.system.query.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.query.shared.module",
})
public class QuerySharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerySharedModuleApplication.class, args);
    }

}
