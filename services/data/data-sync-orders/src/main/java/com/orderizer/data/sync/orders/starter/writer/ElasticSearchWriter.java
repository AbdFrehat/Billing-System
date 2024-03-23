package com.orderizer.data.sync.orders.starter.writer;

import com.orderizer.data.sync.orders.model.entity.Order;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Queue;

@Data
@Slf4j
@RequiredArgsConstructor
public class ElasticSearchWriter implements Runnable {

    private final Queue<List<Order>> orderQueue;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            log.info("The size of the queue is: {}", orderQueue.size());
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
