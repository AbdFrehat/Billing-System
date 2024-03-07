package com.selling.system.export.data.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.services.context-path")
@Data
public class ServicesContextPathConfig {

    private String exportDataCsv;

    private String exportDataXlsx;

    private String exportDataJson;

    private String exportDataXml;

    private String exportDataPdf;

}
