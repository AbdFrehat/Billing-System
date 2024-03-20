package com.orderizer.data.orders.manager.model.request;

import lombok.Data;

import java.util.List;

@Data
public class OrdersUpdateRequest {

    private List<OrderUpdateRequest> orders;

}
