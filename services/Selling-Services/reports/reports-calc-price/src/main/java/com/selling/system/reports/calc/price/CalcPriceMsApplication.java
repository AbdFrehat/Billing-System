package com.selling.system.reports.calc.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.reports.calc.price",
        "com.selling.system.shared.module"
})
public class CalcPriceMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalcPriceMsApplication.class, args);
    }

}
