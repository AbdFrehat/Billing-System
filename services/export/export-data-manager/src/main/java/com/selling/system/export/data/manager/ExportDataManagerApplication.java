package com.selling.system.export.data.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.manager",
                "com.selling.system.shared.module",
        }
)
@EnableDiscoveryClient
public class ExportDataManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataManagerApplication.class, args);
    }

}
