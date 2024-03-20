package com.orderizer.data.orders.manager.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersResponse {

    List<OrderResponse> orders;
}
