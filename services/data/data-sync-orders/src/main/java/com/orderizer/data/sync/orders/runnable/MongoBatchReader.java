package com.orderizer.data.sync.orders.runnable;


import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.entity.Order;
import com.orderizer.data.sync.orders.repository.api.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Queue;

@Slf4j
public class MongoBatchReader implements Runnable {

    private final OrdersRepository ordersRepository;

    private final String storeLocation;

    private final Queue<List<Order>> orderQueue;

    private final int batch;

    private final int delay;
    private int start;
    private int end;

    public MongoBatchReader(OrdersRepository ordersRepository, String storeLocation, Queue<List<Order>> orderQueue, LocalAppConfig localAppConfig) {
        this.ordersRepository = ordersRepository;
        this.storeLocation = storeLocation;
        this.orderQueue = orderQueue;
        this.batch = localAppConfig.getBatch().getSize();
        this.delay = localAppConfig.getBatch().getDelay();
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
        Boolean isFinished = Mono.fromCallable(() -> ordersRepository.fetchOrders(storeLocation, start, end)
                        .collectList())
                .flatMap(orderListMono -> orderListMono)
                .map(orders -> {
                    if (orders.isEmpty()) {
                        return true;
                    } else {
                        log.info("#{} orders are fetched from {} to {} of store {}", orders.size(), start, start + orders.size() - 1, storeLocation);
                        orderQueue.add(orders);
                        return false;
                    }
                })
                .block();
        if (isFinished == null || isFinished) {
            Thread.currentThread().interrupt();
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
