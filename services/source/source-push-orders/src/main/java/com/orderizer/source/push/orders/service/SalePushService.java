package com.orderizer.source.push.orders.service;

import com.orderizer.source.push.orders.config.LocalAppConfig;
import com.orderizer.source.push.orders.push.PushOrderSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class SalePushService implements CommandLineRunner {

    private final PushOrderSaveRequest pushOrderSaveRequest;

    private final LocalAppConfig localAppConfig;

    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(pushOrderSaveRequest, 0, localAppConfig.getEmit().getDuration(), TimeUnit.MILLISECONDS);
    }
}
