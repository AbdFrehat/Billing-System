package com.orderizer.data.update.orders.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersUpdateResponse {

    private List<OrderResponse> orders;
}
