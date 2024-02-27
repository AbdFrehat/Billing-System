package com.selling.system.export.data.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.pdf",
                "com.selling.system.export.shared",
                "com.selling.system.shared.module"
        }
)
@EnableDiscoveryClient
public class ExportDataPdfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataPdfApplication.class, args);
    }

}
