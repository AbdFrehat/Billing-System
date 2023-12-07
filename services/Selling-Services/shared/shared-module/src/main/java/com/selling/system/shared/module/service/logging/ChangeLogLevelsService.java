package com.selling.system.shared.module.service.logging;

import com.selling.system.shared.module.models.log.LogLevels;
import reactor.core.publisher.Mono;

public interface ChangeLogLevelsService {

    Mono<Void> changeLogLevels(LogLevels logLevels);

}
