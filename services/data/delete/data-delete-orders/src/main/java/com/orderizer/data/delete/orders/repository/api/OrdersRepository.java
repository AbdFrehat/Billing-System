package com.orderizer.data.delete.orders.repository.api;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByLocalIdentifiersRequest;
import reactor.core.publisher.Flux;


public interface OrdersRepository {

    Flux<DeleteResult> deleteOrdersByGlobalIdentifier(DeleteOrdersByGlobalIdentifiersRequest request);

    Flux<DeleteResult> deleteOrdersByLocalIdentifier(DeleteOrdersByLocalIdentifiersRequest request);

}
