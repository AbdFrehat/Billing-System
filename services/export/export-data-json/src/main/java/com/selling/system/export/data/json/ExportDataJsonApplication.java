package com.selling.system.export.data.json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.json",
                "com.selling.system.export.shared",
                "com.selling.system.shared.module"
        }
)

public class ExportDataJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataJsonApplication.class, args);
    }

}
