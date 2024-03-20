package com.orderizer.data.orders.manager.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersCountResponse {

    private Long count;
}
