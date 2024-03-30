package com.orderizer.data.sync.orders.config;

import com.orderizer.data.sync.orders.mapper.Mapper;
import com.orderizer.data.sync.orders.model.client.response.Store;
import com.orderizer.data.sync.orders.model.entity.elastic.ElasticOrder;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import com.orderizer.data.sync.orders.model.queue.OrdersBatch;
import com.orderizer.data.sync.orders.repository.api.OrdersElasticRepository;
import com.orderizer.data.sync.orders.repository.api.OrdersMongoRepository;
import com.orderizer.data.sync.orders.service.api.StoresService;
import com.orderizer.data.sync.orders.starter.reader.MongoReaders;
import com.orderizer.data.sync.orders.starter.reader.StoresReader;
import com.orderizer.data.sync.orders.starter.writer.ElasticSearchWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class ResourcesConfig {


    @Bean
    public ArrayBlockingQueue<OrdersBatch> ordersQueue(LocalAppConfig localAppConfig) {
        return new ArrayBlockingQueue<>(localAppConfig.getQueue().getSize());
    }

    @Bean
    public Queue<Store> storesQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public Runnable mongoReaders(OrdersMongoRepository ordersRepository, LocalAppConfig localAppConfig) {
        return new MongoReaders(ordersRepository, localAppConfig, ordersQueue(localAppConfig), storesQueue());
    }

    @Bean
    public Runnable storesReader(StoresService storesService, LocalAppConfig localAppConfig) {
        return new StoresReader(storesService, storesQueue(), localAppConfig);
    }

    @Bean
    public Runnable elasticSearchWriter(LocalAppConfig localAppConfig, OrdersElasticRepository ordersElasticRepository, Mapper<MongoOrder, ElasticOrder> mapper) {
        return new ElasticSearchWriter(ordersQueue(localAppConfig), ordersElasticRepository, localAppConfig, mapper);
    }

}
