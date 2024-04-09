package com.orderizer.export.data.json.handler;

import com.orderizer.core.api.Client;
import com.orderizer.core.api.Mapper;
import com.orderizer.core.api.Mapper2;
import com.orderizer.export.data.json.convertor.api.DataConvertor;
import com.orderizer.export.data.json.model.client.request.OrdersSearchRequest;
import com.orderizer.export.data.json.model.client.response.OrdersResponse;
import com.orderizer.export.data.json.model.request.ExportOrdersFilterRequest;
import com.orderizer.export.data.json.model.response.ExportOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExportOrdersHandler implements HandlerFunction<ServerResponse> {

    private final Mapper<ExportOrdersFilterRequest, OrdersSearchRequest> mapper1;

    private final Mapper2<ExportOrdersFilterRequest, OrdersResponse, ExportOrdersResponse> mapper2;

    private final Client<OrdersSearchRequest, OrdersResponse> client;

    private final DataConvertor dataConvertor;


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(ExportOrdersFilterRequest.class)
                .flatMap(exportOrdersFilterRequest -> mapper1.map(exportOrdersFilterRequest)
                        .flatMap(ordersSearchRequest -> {
                            return client.call(ordersSearchRequest, Map.of("page", 0));
                        })
                        .flatMap(ordersResponse -> mapper2.map(exportOrdersFilterRequest, ordersResponse))
                        .flatMap(dataConvertor::convert)
                        .flatMap(body -> ServerResponse.status(HttpStatus.CREATED)
                                .headers((httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)))
                                .bodyValue(body)));
    }
}
