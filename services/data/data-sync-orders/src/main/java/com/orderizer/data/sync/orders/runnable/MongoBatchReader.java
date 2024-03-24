package com.orderizer.data.sync.orders.runnable;


import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import com.orderizer.data.sync.orders.model.queue.OrdersBatch;
import com.orderizer.data.sync.orders.repository.api.OrdersMongoRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
public class MongoBatchReader implements Runnable {

    private final OrdersMongoRepository ordersMongoRepository;
    private final String storeLocation;
    private final ArrayBlockingQueue<OrdersBatch> orderQueue;
    private final int batch;
    private final int delay;
    private int start;
    private int end;

    public MongoBatchReader(OrdersMongoRepository ordersMongoRepository, String storeLocation, ArrayBlockingQueue<OrdersBatch> orderQueue, LocalAppConfig localAppConfig) {
        this.ordersMongoRepository = ordersMongoRepository;
        this.storeLocation = storeLocation;
        this.orderQueue = orderQueue;
        this.batch = localAppConfig.getBatch().getSize();
        this.delay = localAppConfig.getDelay().getMongoReader();
    }

    @Override
    public synchronized void run() {
        start = 1;
        end = batch;
        while (!Thread.currentThread().isInterrupted()) {
            fetchOrders();
            update();
            sleep();
        }
    }

    private void fetchOrders() {
        List<MongoOrder> mongoOrders = ordersMongoRepository.fetchOrders(storeLocation, start, end).collectList().block();
        if (mongoOrders == null || mongoOrders.isEmpty()) {
            Thread.currentThread().interrupt();
            return;
        }
        log.info("#{} orders are fetched from {} to {} of store {}", mongoOrders.size(), start, start + mongoOrders.size() - 1, storeLocation);
        try {
            orderQueue.put(OrdersBatch.builder()
                    .start(start)
                    .end(end)
                    .storeLocation(storeLocation)
                    .mongoOrders(mongoOrders)
                    .build());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        start = end + 1;
        end += batch;
    }

    private void reset() {
        start = 0;
        end = batch;
    }

    private void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
