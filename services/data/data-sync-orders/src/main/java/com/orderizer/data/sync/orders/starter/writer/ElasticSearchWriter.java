package com.orderizer.data.sync.orders.starter.writer;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.entity.Order;
import com.orderizer.data.sync.orders.runnable.ElasticBatchWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
public class ElasticSearchWriter implements Runnable {

    private final Queue<List<Order>> orderQueue;

    private final LocalAppConfig localAppConfig;

    @Override
    public void run() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(6)) {
            for (int i = 0; i < 8; i++) {
                ElasticBatchWriter writer = new ElasticBatchWriter(orderQueue, localAppConfig);
                executorService.submit(writer);
            }
        }
    }


}
