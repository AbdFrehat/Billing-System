package com.orderizer.data.sync.orders.service.api;

import com.orderizer.data.sync.orders.model.client.response.GetStoresResponse;
import reactor.core.publisher.Mono;

public interface StoresService {

    Mono<GetStoresResponse> fetchStores();
}
