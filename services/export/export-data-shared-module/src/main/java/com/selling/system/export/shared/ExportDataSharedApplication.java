package com.selling.system.export.shared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.orderizer.core"
        }
)
public class ExportDataSharedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataSharedApplication.class, args);
    }

}
