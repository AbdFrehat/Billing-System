package com.sale.persistence.kafka.sale.producer.service;

import com.sale.persistence.kafka.sale.producer.entities.Sale;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService implements MessageProducerService {

    private KafkaTemplate<String, Sale> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, Sale> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(Sale sale) {
        kafkaTemplate.sendDefault(sale.getStoreLocation(), sale);
    }
}
