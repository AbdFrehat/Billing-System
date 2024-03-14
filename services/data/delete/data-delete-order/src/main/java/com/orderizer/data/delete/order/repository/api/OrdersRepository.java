package com.orderizer.data.delete.order.repository.api;

import com.mongodb.client.result.DeleteResult;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Mono<DeleteResult> deleteOrderByGlobalIdentifier(long globalIdentifier);

    Mono<DeleteResult> deleteOrderByLocalIdentifier(long localIdentifier, String storeLocation);
}
