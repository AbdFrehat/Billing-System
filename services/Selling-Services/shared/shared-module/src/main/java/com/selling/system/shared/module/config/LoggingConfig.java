package com.selling.system.shared.module.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

@Configuration
@ConditionalOnProperty(name = "config.logging.enable", havingValue = "true")
public class LoggingConfig {

    @Value("${config.logging.enable}")
    private boolean enable;

    @Value("${config.logging.name:${spring.application.name}.log}")
    private String fileName;

    @Value("${config.logging.path:./logs/}")
    private String logFilePath;

    @Value("${config.logging.pattern:%d [%thread] %-5level %logger{35} - %msg%n}")
    private String logPattern;

    @Value("${config.logging.archive.pattern:.%d{yyyy-MM-dd}.%i.gz}")
    private String archivePattern;

    @Value("${config.logging.archive.totalSize:10MB}")
    private String totalSize;

    @Value("${config.logging.archive.maxSize:1MB}")
    private String maxSize;

    @Value("${config.logging.archive.numberOfFiles:10}")
    private int maxHistory;

    private LoggerContext loggerContext;

    @PostConstruct
    public void init() {
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        initRollingAppender();
    }

    private void initRollingAppender() {
        RollingFileAppender<String> appender = new RollingFileAppender<>();
        appender.setFile(logFilePath + fileName);
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(logPattern);
        encoder.setContext(loggerContext);
        encoder.start();
        initRollingPolicy(appender, (Encoder) encoder);
    }

    private void initRollingPolicy(RollingFileAppender<String> appender, Encoder<String> encoder) {
        SizeAndTimeBasedRollingPolicy<String> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setFileNamePattern(logFilePath + fileName.replaceAll("\\..*", "") + archivePattern);
        rollingPolicy.setTotalSizeCap(FileSize.valueOf(totalSize));
        rollingPolicy.setMaxFileSize(FileSize.valueOf(maxSize));
        rollingPolicy.setMaxHistory(maxHistory);
        rollingPolicy.setParent(appender);
        rollingPolicy.setContext(loggerContext);
        rollingPolicy.start();
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.setRollingPolicy(rollingPolicy);
        appender.start();
        loggerContext.getLogger(ROOT_LOGGER_NAME).addAppender((Appender) appender);
    }

}
