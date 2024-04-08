package com.orderizer.kafka.orders.consumer.consumers;

import com.orderizer.kafka.orders.consumer.config.LocalAppConfig;
import com.orderizer.kafka.orders.consumer.constant.Channels;
import com.orderizer.kafka.orders.consumer.model.entity.DataDeleteOrderEntity;
import com.orderizer.kafka.orders.consumer.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.selling.system.shared.module.api.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;


@Slf4j
@Configuration
public class DataDeleteOrdersConsumer {

    private final WebClient webClient;

    private final Mapper<DataDeleteOrderEntity, Long> mapper1;

    private final Mapper<List<Long>, DeleteOrdersByGlobalIdentifiersRequest> mapper2;

    public DataDeleteOrdersConsumer(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder, Mapper<DataDeleteOrderEntity, Long> mapper1, Mapper<List<Long>, DeleteOrdersByGlobalIdentifiersRequest> mapper2) {
        this.mapper1 = mapper1;
        this.mapper2 = mapper2;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataManager()).build();
    }

    @Bean(Channels.DATA_DELETE_ORDERS)
    @Transactional
    public Consumer<Message<List<DataDeleteOrderEntity>>> dataDeleteOrders() {
        return messages -> {
            Flux.fromIterable(messages.getPayload()).flatMap(mapper1::map)
                    .collectList()
                    .flatMap(mapper2::map)
                    .flatMap(deleteOrdersByGlobalIdentifiersRequest -> webClient.post()
                            .uri(uriBuilder -> uriBuilder.path("/orders/delete/batch/global").build())
                            .bodyValue(deleteOrdersByGlobalIdentifiersRequest)
                            .retrieve()
                            .toBodilessEntity())
                    .subscribe();
        };
    }
}
