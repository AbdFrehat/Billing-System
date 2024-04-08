package com.orderizer.core.service.logging;

import com.orderizer.core.models.log.LogLevels;
import reactor.core.publisher.Mono;

public interface ChangeLogLevelsService {

    Mono<Void> changeLogLevels(LogLevels logLevels);

}
