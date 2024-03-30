package com.orderizer.data.update.orders.model.request;

import lombok.Data;

import java.util.List;

@Data
public class OrdersUpdateRequest {

    private List<OrderRequest> orders;

}
