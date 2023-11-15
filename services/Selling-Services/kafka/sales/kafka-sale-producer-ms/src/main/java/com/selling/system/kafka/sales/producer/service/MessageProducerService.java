package com.selling.system.kafka.sales.producer.service;

import com.selling.system.shared.module.models.entities.Sale;

public interface MessageProducerService {

    void sendMessage(Sale sale);

}
