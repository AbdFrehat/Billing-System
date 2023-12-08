package com.selling.system.kafka.sales.producer.service;

import com.selling.system.shared.module.models.commands.ModifyCommand;
import com.selling.system.shared.module.models.entities.Sale;

import java.util.List;

public interface MessageProducerService {

    void sendCommand(ModifyCommand modifyCommand);
}
