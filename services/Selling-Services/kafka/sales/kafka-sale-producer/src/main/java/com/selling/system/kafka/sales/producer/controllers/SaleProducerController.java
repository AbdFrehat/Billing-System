package com.selling.system.kafka.sales.producer.controllers;

import com.selling.system.kafka.sales.producer.service.MessageProducerService;
import com.selling.system.shared.module.models.commands.ModifyCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleProducerController {
    private final MessageProducerService messageProducerService;

    public SaleProducerController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @PostMapping
    public ResponseEntity<Void> saveSale(@RequestBody ModifyCommand modifyCommand) {
        messageProducerService.sendCommand(modifyCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
