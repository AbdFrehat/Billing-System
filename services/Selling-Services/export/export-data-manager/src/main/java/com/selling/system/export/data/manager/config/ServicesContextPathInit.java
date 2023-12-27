package com.selling.system.export.data.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.export-data-csv}")
    private String exportDataCsvContextPath;
    @Value("${config.services.context-path.export-data-pdf}")
    private String exportDataPdfContextPath;
    @Value("${config.services.context-path.export-data-json}")
    private String exportDataJsonContextPath;
    @Value("${config.services.context-path.export-data-xlsx}")
    private String exportDataXlsxContextPath;
    @Value("${config.services.context-path.export-data-xml}")
    private String exportDataXmlContextPath;

    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "export-data-csv", exportDataCsvContextPath,
                "export-data-pdf", exportDataPdfContextPath,
                "export-data-xlsx", exportDataXlsxContextPath,
                "export-data-xml", exportDataXmlContextPath,
                "export-data-json", exportDataJsonContextPath
        );
    }
}
