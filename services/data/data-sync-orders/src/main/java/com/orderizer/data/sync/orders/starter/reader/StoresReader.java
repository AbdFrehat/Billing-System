package com.orderizer.data.sync.orders.starter.reader;


import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.client.response.Store;
import com.orderizer.data.sync.orders.service.api.StoresService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Queue;

@RequiredArgsConstructor
public class StoresReader implements Runnable {

    private final StoresService storesService;

    private final Queue<Store> storesQueue;

    private final LocalAppConfig localAppConfig;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            storesService.fetchStores()
                    .flatMapMany(storesResponse -> Flux.fromIterable(storesResponse.getStores()))
                    .doOnNext(store -> {
                        if (!storesQueue.contains(store)) {
                            storesQueue.add(store);
                        }
                    })
                    .subscribe();
            sleep();
        }

    }

    private void sleep() {
        try {
            Thread.sleep(localAppConfig.getDelay().getStoresReader());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
