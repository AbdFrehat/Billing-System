package com.selling.system.shared.module.controllers.logging;

import com.selling.system.shared.module.models.log.LogLevels;
import com.selling.system.shared.module.service.logging.ChangeLogLevelsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/logging")
public class ChangeLogLevelsController {

    private final ChangeLogLevelsService changeLogLevelsService;

    public ChangeLogLevelsController(ChangeLogLevelsService changeLogLevelsService) {
        this.changeLogLevelsService = changeLogLevelsService;
    }

    @PostMapping
    public ResponseEntity<Mono<Void>> changeLogLevels(@RequestBody @Valid LogLevels logLevels) {
        return ResponseEntity.ok().body(changeLogLevelsService.changeLogLevels(logLevels));
    }

}
