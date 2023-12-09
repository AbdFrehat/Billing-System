package com.selling.system.modify.update.sales.controller;

import com.selling.system.modify.shared.sales.client.KafkaProducerClient;
import com.selling.system.shared.module.models.commands.ModifyCommand;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.CommandType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ModifyUpdateCommandController {

    private final KafkaProducerClient kafkaProducerClient;

    public ModifyUpdateCommandController(KafkaProducerClient kafkaProducerClient) {
        this.kafkaProducerClient = kafkaProducerClient;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> updateSale(@RequestBody Sale sale) {
        return kafkaProducerClient.send(ModifyCommand.builder().commandType(CommandType.UPDATE_SALE).data(sale).build());
    }

    @PostMapping("/multi")
    public Mono<ResponseEntity<Void>> updateSales(@RequestBody List<Sale> sales) {
        return kafkaProducerClient.send(ModifyCommand.builder().commandType(CommandType.UPDATE_SALES).data(sales).build());
    }

}
