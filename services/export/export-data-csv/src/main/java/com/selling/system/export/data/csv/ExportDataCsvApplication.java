package com.selling.system.export.data.csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
        scanBasePackages = {
                "com.selling.system.export.data.csv",
                "com.selling.system.export.shared",
                "com.orderizer.core"
        }
)

public class ExportDataCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportDataCsvApplication.class, args);
    }

}
