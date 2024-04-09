package com.orderizer.export.data.json.mapper;

import com.orderizer.core.api.Mapper2;
import com.orderizer.export.data.json.model.client.response.OrdersResponse;
import com.orderizer.export.data.json.model.request.ExportOrdersFilterRequest;
import com.orderizer.export.data.json.model.response.ExportOrdersResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ExportOrdersResponseMapper implements Mapper2<ExportOrdersFilterRequest, OrdersResponse, ExportOrdersResponse> {
    @Override
    public Mono<ExportOrdersResponse> map(ExportOrdersFilterRequest exportOrdersFilterRequest, OrdersResponse ordersResponse) {
        return Mono.defer(() -> Mono.just(ExportOrdersResponse.builder()
                .filter(exportOrdersFilterRequest)
                .data(ordersResponse)
                .build()));
    }
}
