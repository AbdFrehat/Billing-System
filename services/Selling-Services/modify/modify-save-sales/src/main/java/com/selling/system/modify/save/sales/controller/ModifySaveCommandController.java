package com.selling.system.modify.save.sales.controller;

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
public class ModifySaveCommandController {

    private final KafkaProducerClient kafkaProducerClient;

    public ModifySaveCommandController(KafkaProducerClient kafkaProducerClient) {
        this.kafkaProducerClient = kafkaProducerClient;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> saveSale(@RequestBody Sale sale) {
        return kafkaProducerClient.send(ModifyCommand.builder().commandType(CommandType.SAVE_SALE).data(sale).build());
    }

    @PostMapping("/multi")
    public Mono<ResponseEntity<Void>> saveSales(@RequestBody List<Sale> sales) {
        return kafkaProducerClient.send(ModifyCommand.builder().commandType(CommandType.SAVE_SALES).data(sales).build());
    }

}
