package com.selling.system.data.sales.get.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersCountResponse {

    private Long count;
}
