package com.orderizer.data.sync.orders.starter.writer;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.mapper.Mapper;
import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import com.orderizer.data.sync.orders.model.queue.OrdersBatch;
import com.orderizer.data.sync.orders.repository.api.OrdersElasticRepository;
import com.orderizer.data.sync.orders.runnable.ElasticBatchWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
public class ElasticSearchWriter implements Runnable {

    private final Queue<OrdersBatch> orderQueue;
    private final OrdersElasticRepository ordersElasticRepository;
    private final LocalAppConfig localAppConfig;
    private final Mapper<MongoOrder, ElasticOrder> mapper;


    @Override
    public void run() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < localAppConfig.getThreads().getElasticsearch().getSize(); i++) {
                ElasticBatchWriter writer = new ElasticBatchWriter(orderQueue, ordersElasticRepository, localAppConfig, mapper);
                executorService.submit(writer);
            }
        }
    }


}
