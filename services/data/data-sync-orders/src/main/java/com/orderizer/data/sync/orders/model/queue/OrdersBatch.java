package com.orderizer.data.sync.orders.model.queue;

import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersBatch {

    private String storeLocation;

    private int start;

    private int end;

    private List<MongoOrder> mongoOrders;

}
