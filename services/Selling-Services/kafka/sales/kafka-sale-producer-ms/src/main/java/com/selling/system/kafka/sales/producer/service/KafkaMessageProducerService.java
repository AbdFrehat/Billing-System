package com.selling.system.kafka.sales.producer.service;

import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService implements MessageProducerService {

    private final KafkaTemplate<String, Sale> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, Sale> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(Sale sale) {
        kafkaTemplate.sendDefault(sale.getStoreLocation(), sale);
    }
}
