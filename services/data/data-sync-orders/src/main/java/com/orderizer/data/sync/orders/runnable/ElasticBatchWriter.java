package com.orderizer.data.sync.orders.runnable;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Queue;

@RequiredArgsConstructor
@Slf4j
public class ElasticBatchWriter implements Runnable {

    private final Queue<List<Order>> orderQueue;

    private final LocalAppConfig localAppConfig;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            List<Order> orders = orderQueue.poll();
            if (orders != null) {
                log.info("#{} orders are pulled in thread {}", orders.size(), Thread.currentThread().threadId());
            } else {
                log.warn("The queue is empty in thread {}", Thread.currentThread().threadId());
            }
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(localAppConfig.getDelay().getElasticWriter());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
