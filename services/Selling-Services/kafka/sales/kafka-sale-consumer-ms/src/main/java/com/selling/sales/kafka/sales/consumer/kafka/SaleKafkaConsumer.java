package com.selling.sales.kafka.sales.consumer.kafka;

import com.selling.sales.kafka.sales.consumer.client.DataManagerClient;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.QueryMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleKafkaConsumer {

    private final DataManagerClient dataManagerClient;

    public SaleKafkaConsumer(DataManagerClient dataManagerClient) {
        this.dataManagerClient = dataManagerClient;
    }

    @KafkaListener(topics = "${spring.kafka.default-topic}", containerFactory = "batchFactory")
    @Transactional
    public void consumerSaleMessage(@Payload List<Sale> sales) {
        dataManagerClient
                .saveSales(QueryCommand.builder()
                        .payload(sales)
                        .queryMethod(QueryMethod.SAVE_SALES)
                        .build())
                .subscribe();
    }

}
