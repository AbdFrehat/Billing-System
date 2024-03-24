package com.orderizer.data.sync.orders.starter.reader;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.client.response.Store;
import com.orderizer.data.sync.orders.model.queue.OrdersBatch;
import com.orderizer.data.sync.orders.repository.api.OrdersMongoRepository;
import com.orderizer.data.sync.orders.runnable.MongoBatchReader;
import lombok.RequiredArgsConstructor;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public class MongoReaders implements Runnable {

    private final OrdersMongoRepository ordersRepository;

    private final LocalAppConfig localAppConfig;

    private final ArrayBlockingQueue<OrdersBatch> orderQueue;

    private final Queue<Store> storesQueue;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
                storesQueue.parallelStream().forEach(store -> {
                    MongoBatchReader reader = new MongoBatchReader(ordersRepository, store.getStoreLocation(), orderQueue, localAppConfig);
                    executorService.submit(reader);
                });
            }
        }
    }
}
