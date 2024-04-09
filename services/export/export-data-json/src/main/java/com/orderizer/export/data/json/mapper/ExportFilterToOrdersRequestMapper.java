package com.orderizer.export.data.json.mapper;

import com.orderizer.core.api.Mapper;
import com.orderizer.export.data.json.model.client.request.OrdersSearchRequest;
import com.orderizer.export.data.json.model.field.ExactField;
import com.orderizer.export.data.json.model.field.RangeDateField;
import com.orderizer.export.data.json.model.request.ExportOrdersFilterRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static com.orderizer.core.utils.StringUtil.isEmpty;

@Component
public class ExportFilterToOrdersRequestMapper implements Mapper<ExportOrdersFilterRequest, OrdersSearchRequest> {

    @Override
    public Mono<OrdersSearchRequest> map(ExportOrdersFilterRequest exportOrdersFilterRequest) {
        return Mono.fromCallable(() -> {
                    OrdersSearchRequest.OrdersSearchRequestBuilder ordersSearchRequestBuilder = OrdersSearchRequest.builder();
                    List<ExactField> exactFields = new ArrayList<>();
                    if (exportOrdersFilterRequest.getCouponUsed() != null) {
                        exactFields.add(ExactField.builder()
                                .field("couponUsed")
                                .value(exportOrdersFilterRequest.getCouponUsed()).build());
                    }
                    if (!isEmpty(exportOrdersFilterRequest.getPurchaseMethod())) {
                        exactFields.add(ExactField.builder()
                                .field("purchaseMethod")
                                .value(exportOrdersFilterRequest.getPurchaseMethod())
                                .build());
                    }
                    if (!isEmpty(exportOrdersFilterRequest.getStoreLocation())) {
                        exactFields.add(ExactField.builder()
                                .field("storeLocation")
                                .value(exportOrdersFilterRequest.getStoreLocation())
                                .build());
                    }
                    if (exportOrdersFilterRequest.getSatisfaction() != null) {
                        exactFields.add(ExactField.builder()
                                .field("customer.satisfaction")
                                .value(exportOrdersFilterRequest.getSatisfaction())
                                .build());
                    }
                    if (!exactFields.isEmpty()) {
                        ordersSearchRequestBuilder.exactFields(exactFields);
                    }
                    ordersSearchRequestBuilder.rangeDateFields(List.of(RangeDateField.builder()
                            .min(exportOrdersFilterRequest.getFromDate())
                            .max(exportOrdersFilterRequest.getToDate())
                            .field("orderDate")
                            .build()));
                    return ordersSearchRequestBuilder.build();
                }
        );
    }
}