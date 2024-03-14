package com.orderizer.data.delete.orders.model.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteOrdersByGlobalIdentifiersRequest {

    private List<Long> globalIdentifiers;
}
