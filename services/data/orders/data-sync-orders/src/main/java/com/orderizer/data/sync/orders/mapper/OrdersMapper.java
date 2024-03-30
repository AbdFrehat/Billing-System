package com.orderizer.data.sync.orders.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.ZoneOffset.UTC;

@Component
@RequiredArgsConstructor
public class OrdersMapper implements Mapper<MongoOrder, ElasticOrder> {

    private final ObjectMapper objectMapper;


    @Override
    public ElasticOrder map(MongoOrder mongoOrder) {
        return ElasticOrder.builder()
                .id(mongoOrder.getId())
                .orderDate(mongoOrder.getOrderDate().atZone(UTC))
                .items(mongoOrder.getItems())
                .customer(mongoOrder.getCustomer())
                .globalIdentifier(mongoOrder.getGlobalIdentifier())
                .localIdentifier(mongoOrder.getLocalIdentifier())
                .totalPrice(mongoOrder.getTotalPrice())
                .purchaseMethod(mongoOrder.getPurchaseMethod())
                .storeLocation(mongoOrder.getStoreLocation())
                .couponUsed(mongoOrder.isCouponUsed())
                .build();
    }
}
