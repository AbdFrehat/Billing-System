package com.orderizer.data.sync.orders.runnable;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.mapper.Mapper;
import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import com.orderizer.data.sync.orders.model.queue.OrdersBatch;
import com.orderizer.data.sync.orders.repository.api.OrdersElasticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static com.selling.system.shared.module.utils.CollectionUtil.isEmpty;

@RequiredArgsConstructor
@Slf4j
public class ElasticBatchWriter implements Runnable {

    private final Queue<OrdersBatch> orderQueue;
    private final OrdersElasticRepository ordersElasticRepository;
    private final LocalAppConfig localAppConfig;
    private final Mapper<MongoOrder, ElasticOrder> mapper;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            OrdersBatch ordersBatch = orderQueue.poll();
            if (ordersBatch != null && ordersBatch.getMongoOrders() != null) {
                List<ElasticOrder> elasticOrders = ordersElasticRepository.fetchOrders(ordersBatch.getStoreLocation(), ordersBatch.getStart(), ordersBatch.getEnd())
                        .collectList()
                        .switchIfEmpty(Mono.just(List.of()))
                        .block();
                List<MongoOrder> mongoOrders = ordersBatch.getMongoOrders();
                List<ElasticOrder> updatedOrders = new ArrayList<>();
                List<ElasticOrder> deletedOrders = new ArrayList<>();
                if (isEmpty(elasticOrders)) {
                    updatedOrders.addAll(mongoOrders.stream().map(mapper::map).toList());
                } else {
                    for (MongoOrder mongoOrder : mongoOrders) {
                        elasticOrders.stream().filter(elasticOrder -> elasticOrder.getGlobalIdentifier() == mongoOrder.getGlobalIdentifier()).findFirst()
                                .ifPresentOrElse((elasticOrder -> {
                                    if (!elasticOrder.equals(mongoOrder)) {
                                        updatedOrders.add(mapper.map(mongoOrder));
                                    }
                                }), () -> {
                                    updatedOrders.add(mapper.map(mongoOrder));
                                });
                    }
                    for (ElasticOrder elasticOrder : elasticOrders) {
                        Optional<MongoOrder> optionalMongoOrder = mongoOrders.stream().filter(mongoOrder -> elasticOrder.getGlobalIdentifier() == mongoOrder.getGlobalIdentifier()).findFirst();
                        if (optionalMongoOrder.isEmpty()) {
                            deletedOrders.add(elasticOrder);
                        }
                    }
                }
                Mono<List<ElasticOrder>> updateMono = updatedOrders.isEmpty() ?
                        Mono.just(List.of()) :
                        ordersElasticRepository.updateOrders(updatedOrders).collectList();
                Mono<List<String>> deleteMono = deletedOrders.isEmpty() ?
                        Mono.just(List.of()) :
                        ordersElasticRepository.deleteOrders(deletedOrders).collectList();
                Mono.zip(updateMono, deleteMono).block();
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
