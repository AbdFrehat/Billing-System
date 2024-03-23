package com.orderizer.data.sync.orders.starter.reader;


import com.orderizer.data.sync.orders.model.client.response.Store;
import com.orderizer.data.sync.orders.service.api.StoresService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Queue;

@RequiredArgsConstructor
@Data
public class StoresReader implements Runnable {

    private final StoresService storesService;

    private final Queue<Store> storesQueue;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            storesService.fetchStores()
                    .flatMapMany(storesResponse -> Flux.fromIterable(storesResponse.getStores()))
                    .doOnNext(storesQueue::add)
                    .subscribe();
            sleep();
        }

    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
