package com.selling.sales.kafka.sales.consumer.kafka;

import com.selling.sales.kafka.sales.consumer.entities.SaleDocument;
import com.selling.sales.kafka.sales.consumer.repositories.SalesRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleKafkaConsumer {

    private final SalesRepository salesRepository;

    public SaleKafkaConsumer(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @KafkaListener(topics = "${spring.kafka.default-topic}")
    @Transactional
    public void consumerSaleMessage(@Payload SaleDocument saleDocument) {
        this.salesRepository.save(saleDocument).subscribe();
    }

}
