package com.orderizer.data.get.search.orders.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersCountResponse {

    private Long count;
}
