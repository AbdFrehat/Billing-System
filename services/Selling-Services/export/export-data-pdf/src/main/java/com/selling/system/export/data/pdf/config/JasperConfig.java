package com.selling.system.export.data.pdf.config;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Map;

@Configuration
public class JasperConfig {

    @Bean
    Map<String, JasperReport> jasperReportMap(ResourceLoader resourceLoader) {
        try {
            ;
            return Map.of(
                    "sales", JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jasper/sales-export-report.jrxml").getInputStream()),
                    "customer", JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jasper/customer-report.jrxml").getInputStream()),
                    "items", JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jasper/items-report.jrxml").getInputStream())
            );
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

}
