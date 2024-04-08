package com.selling.system.export.data.xlsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.xlsx",
                "com.selling.system.export.shared",
                "com.orderizer.core"
        }
)

public class ExportDataXlsxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataXlsxApplication.class, args);
    }

}
