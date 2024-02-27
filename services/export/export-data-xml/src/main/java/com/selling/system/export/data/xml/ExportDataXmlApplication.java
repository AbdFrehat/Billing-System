package com.selling.system.export.data.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.xml",
                "com.selling.system.export.shared",
                "com.selling.system.shared.module"
        }
)
@EnableDiscoveryClient
public class ExportDataXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataXmlApplication.class, args);
    }

}
