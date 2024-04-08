package com.orderizer.kafka.orders.consumer.model.request;

import lombok.Data;

import java.util.List;

@Data
public class OrdersUpdateRequest {

    private List<OrderUpdateRequest> orders;

}
