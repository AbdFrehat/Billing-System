package com.selling.system.export.data.xlsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.xlsx",
                "com.selling.system.export.shared",
                "com.selling.system.shared.module"
        }
)
@EnableDiscoveryClient
public class ExportDataXlsxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataXlsxApplication.class, args);
    }

}
