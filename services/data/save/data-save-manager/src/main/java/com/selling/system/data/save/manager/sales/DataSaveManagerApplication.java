package com.selling.system.data.save.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.save.manager.sales",
        "com.selling.system.shared.module"
})

public class DataSaveManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSaveManagerApplication.class, args);
    }

}
