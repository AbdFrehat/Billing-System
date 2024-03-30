package com.orderizer.data.update.orders.manager.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersUpdateResponse {

    private List<OrdersUpdateResponse> orders;
}
