package com.sale.persistence.kafka.sale.producer.service;

import com.sale.persistence.kafka.sale.producer.entities.Sale;

public interface MessageProducerService {

    void sendMessage(Sale sale);

}
