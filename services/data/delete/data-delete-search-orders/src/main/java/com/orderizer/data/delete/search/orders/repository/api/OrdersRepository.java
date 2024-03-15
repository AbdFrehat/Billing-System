package com.orderizer.data.delete.search.orders.repository.api;


import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.delete.search.orders.model.request.DeleteOrdersRequest;
import reactor.core.publisher.Flux;

public interface OrdersRepository {

    Flux<DeleteResult> deleteOrders(DeleteOrdersRequest deleteOrdersRequest);


}
