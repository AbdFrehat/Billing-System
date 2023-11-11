package com.selling.system.persistence.sales.producer.service;

import com.selling.system.persistence.sales.producer.entities.Sale;

public interface MessageProducerService {

    void sendMessage(Sale sale);

}
