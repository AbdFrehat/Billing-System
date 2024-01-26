package com.selling.system.kafka.sales.producer.service;

import com.selling.system.shared.module.models.commands.ModifyCommand;

public interface MessageProducerService {

    void sendCommand(ModifyCommand modifyCommand);
}
