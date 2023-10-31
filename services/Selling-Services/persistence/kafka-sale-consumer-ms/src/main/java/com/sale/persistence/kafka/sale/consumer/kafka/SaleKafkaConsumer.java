package com.sale.persistence.kafka.sale.consumer.kafka;

import com.sale.persistence.kafka.sale.consumer.entities.Sale;
import com.sale.persistence.kafka.sale.consumer.repositories.SalesRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleKafkaConsumer {

    private SalesRepository salesRepository;

    public SaleKafkaConsumer(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @KafkaListener(topics = "${spring.kafka.default-topic}")
    @Transactional
    public void consumerSaleMessage(@Payload Sale sale) {
        this.salesRepository.save(sale).subscribe();
    }

}
