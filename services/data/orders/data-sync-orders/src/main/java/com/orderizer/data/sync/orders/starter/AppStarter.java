package com.orderizer.data.sync.orders.starter;

import com.orderizer.data.sync.orders.repository.api.OrdersElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class AppStarter implements CommandLineRunner {

    private final List<Runnable> runnable;

    private final OrdersElasticRepository ordersElasticRepository;

    @Override
    public void run(String... args) throws Exception {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            runnable.forEach(executorService::submit);
        }
    }
}
