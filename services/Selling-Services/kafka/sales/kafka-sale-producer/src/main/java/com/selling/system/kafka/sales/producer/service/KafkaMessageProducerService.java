package com.selling.system.kafka.sales.producer.service;

import com.selling.system.shared.module.models.commands.ModifyCommand;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService implements MessageProducerService {

    private final KafkaTemplate<String, ModifyCommand> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, ModifyCommand> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendCommand(ModifyCommand modifyCommand) {
        kafkaTemplate.sendDefault(modifyCommand.getCommandType().name(), modifyCommand);
    }
}
