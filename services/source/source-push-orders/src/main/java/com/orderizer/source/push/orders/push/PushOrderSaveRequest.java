package com.orderizer.source.push.orders.push;

import com.orderizer.source.push.orders.config.LocalAppConfig;
import com.orderizer.source.random.generator.orders.generator.api.OrdersGenerator;
import com.orderizer.source.random.generator.orders.model.emit.Max;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PushOrderSaveRequest implements Runnable {

    private final LocalAppConfig localAppConfig;

    private final WebClient webClient;

    private final OrdersGenerator ordersGenerator;


    public PushOrderSaveRequest(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder, OrdersGenerator ordersGenerator) {
        this.localAppConfig = localAppConfig;
        this.ordersGenerator = ordersGenerator;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDestination()).build();
    }

    @Override
    public void run() {
        ordersGenerator.generateRandomSaveOrderRequest(Max.builder()
                        .items(localAppConfig.getEmit().getMax().getItems())
                        .tags(localAppConfig.getEmit().getMax().getTags())
                        .build())
                .flatMap(orderSaveRequest -> webClient
                        .post()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(orderSaveRequest)
                        .retrieve()
                        .toBodilessEntity())
                .subscribe();
    }
}
