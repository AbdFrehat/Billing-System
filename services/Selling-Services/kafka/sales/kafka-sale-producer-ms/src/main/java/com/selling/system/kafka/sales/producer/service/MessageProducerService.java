package com.selling.system.kafka.sales.producer.service;

import com.selling.system.kafka.sales.producer.entities.Sale;

public interface MessageProducerService {

    void sendMessage(Sale sale);

}
