package com.orderizer.core.service.logging;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.orderizer.core.models.log.LogLevel;
import com.orderizer.core.models.log.LogLevels;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChangeLogLevelsServiceImpl implements ChangeLogLevelsService {


    @Override
    public Mono<Void> changeLogLevels(LogLevels logLevels) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger;
        for (LogLevel logLevel : logLevels.getLogLevels()) {
            logger = loggerContext.getLogger(logLevel.getPackagePath());
            logger.setLevel(logLevel.getLevel());
        }
        return Mono.empty();
    }
}
