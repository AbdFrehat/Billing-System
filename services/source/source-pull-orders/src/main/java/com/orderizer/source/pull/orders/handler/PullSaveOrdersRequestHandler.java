package com.orderizer.source.pull.orders.handler;

import com.orderizer.source.pull.orders.config.LocalAppConfig;
import com.orderizer.source.random.generator.orders.generator.api.OrdersGenerator;
import com.orderizer.source.random.generator.orders.model.emit.Max;
import com.orderizer.source.random.generator.orders.model.request.OrderSaveRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PullSaveOrdersRequestHandler implements HandlerFunction<ServerResponse> {

    private final LocalAppConfig localAppConfig;

    private final OrdersGenerator ordersGenerator;

    @Override
    @NonNull
    public Mono<ServerResponse> handle(@NonNull ServerRequest request) {
        var serverSentEventFlux = Flux.interval(Duration.ofMillis(localAppConfig.getEmit().getDuration()))
                .flatMap(sequence -> ordersGenerator.generateRandomSaveOrderRequest(Max.builder()
                        .items(localAppConfig.getEmit().getMax().getItems())
                        .tags(localAppConfig.getEmit().getMax().getTags())
                        .build()).map(orderSaveRequest -> ServerSentEvent.<OrderSaveRequest>builder().data(orderSaveRequest).build()));
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(serverSentEventFlux, ServerSentEvent.class);
    }
}
