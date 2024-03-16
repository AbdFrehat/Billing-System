package com.selling.system.shared.module.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

@Configuration
@ConditionalOnProperty(name = "config.logging.enable", havingValue = "true")
@RequiredArgsConstructor
public class LoggingConfig {

    private LoggerContext loggerContext;

    private final AppConfig appConfig;

    private final Environment environment;

    @PostConstruct
    public void init() {
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        initRollingAppender();
    }

    private void initRollingAppender() {
        if(appConfig.getLogging() != null && appConfig.getLogging().getFileName() == null) {
            appConfig.getLogging().setFileName(environment.getProperty("spring.application.name") + ".log");
        }
        RollingFileAppender<String> appender = new RollingFileAppender<>();
        appender.setFile(appConfig.getLogging().getLogFilePath() + appConfig.getLogging().getFileName());
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(appConfig.getLogging().getLogPattern());
        encoder.setContext(loggerContext);
        encoder.start();
        initRollingPolicy(appender, (Encoder) encoder);
    }

    private void initRollingPolicy(RollingFileAppender<String> appender, Encoder<String> encoder) {
        SizeAndTimeBasedRollingPolicy<String> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setFileNamePattern(appConfig.getLogging().getLogFilePath() + appConfig.getLogging().getFileName().replaceAll("\\..*", "") + appConfig.getLogging().getArchivePattern());
        rollingPolicy.setTotalSizeCap(FileSize.valueOf(appConfig.getLogging().getTotalSize()));
        rollingPolicy.setMaxFileSize(FileSize.valueOf(appConfig.getLogging().getMaxSize()));
        rollingPolicy.setMaxHistory(appConfig.getLogging().getMaxHistory());
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
