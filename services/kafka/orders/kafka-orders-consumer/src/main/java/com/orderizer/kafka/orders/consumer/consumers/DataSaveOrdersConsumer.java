package com.orderizer.kafka.orders.consumer.consumers;

import com.orderizer.kafka.orders.consumer.constant.Channels;
import com.orderizer.kafka.orders.consumer.model.entity.DataSaveOrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.function.Consumer;


@Slf4j
@Configuration
public class DataSaveOrdersConsumer {

    @Bean(Channels.DATA_SAVE_ORDERS)
    public Consumer<Message<List<DataSaveOrderEntity>>> dataSaveOrders() {
        return message -> {
            log.info("{}", message.getPayload());
        };
    }
}
