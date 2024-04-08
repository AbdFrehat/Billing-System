package com.orderizer.kafka.orders.consumer.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrdersByGlobalIdentifiersRequest {

    private List<Long> globalIdentifiers;
}
