package com.selling.sales.kafka.sales.consumer.kafka;

import com.selling.sales.kafka.sales.consumer.client.DataManagerClient;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.ModifyCommand;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleKafkaConsumer {

    private final DataManagerClient dataManagerClient;

    public SaleKafkaConsumer(DataManagerClient dataManagerClient) {
        this.dataManagerClient = dataManagerClient;
    }

    @KafkaListener(topics = "${spring.kafka.default-topic}", containerFactory = "batchFactory")
    @Transactional
    public void consumerSaleMessage(@Payload List<ModifyCommand> modifyCommands) {
        dataManagerClient.sendCommands(
                modifyCommands.stream().map(modifyCommand -> DataCommand.builder()
                        .commandType(modifyCommand.getCommandType())
                        .payload(modifyCommand.getData())
                        .build()).toList()
        ).subscribe();
    }

}
