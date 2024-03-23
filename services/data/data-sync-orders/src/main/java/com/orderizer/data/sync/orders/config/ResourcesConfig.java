package com.orderizer.data.sync.orders.config;

import com.orderizer.data.sync.orders.model.client.response.Store;
import com.orderizer.data.sync.orders.model.entity.Order;
import com.orderizer.data.sync.orders.repository.api.OrdersRepository;
import com.orderizer.data.sync.orders.service.api.StoresService;
import com.orderizer.data.sync.orders.starter.reader.MongoReaders;
import com.orderizer.data.sync.orders.starter.reader.StoresReader;
import com.orderizer.data.sync.orders.starter.writer.ElasticSearchWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class ResourcesConfig {


    @Bean
    public Queue<List<Order>> ordersQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public Queue<Store> storesQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public Runnable mongoReaders(OrdersRepository ordersRepository, LocalAppConfig localAppConfig, Queue<Store> storesQueue) {
        return new MongoReaders(ordersRepository, localAppConfig, ordersQueue(), storesQueue);
    }

    @Bean
    public Runnable storesReader(StoresService storesService, Queue<Store> storesQueue) {
        return new StoresReader(storesService, storesQueue);
    }

    @Bean
    public Runnable elasticSearchWriter() {
        return new ElasticSearchWriter(ordersQueue());
    }

}
