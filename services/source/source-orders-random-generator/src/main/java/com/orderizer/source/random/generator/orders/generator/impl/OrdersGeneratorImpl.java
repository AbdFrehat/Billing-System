package com.orderizer.source.random.generator.orders.generator.impl;


import com.orderizer.source.random.generator.orders.generator.api.OrdersGenerator;
import com.orderizer.source.random.generator.orders.model.common.Customer;
import com.orderizer.source.random.generator.orders.model.common.Item;
import com.orderizer.source.random.generator.orders.model.data.CustomerData;
import com.orderizer.source.random.generator.orders.model.data.ItemsData;
import com.orderizer.source.random.generator.orders.model.data.OrdersData;
import com.orderizer.source.random.generator.orders.model.emit.Max;
import com.orderizer.source.random.generator.orders.model.request.OrderSaveRequest;
import com.orderizer.core.models.enums.PurchaseMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class OrdersGeneratorImpl implements OrdersGenerator {


    private final CustomerData customerData;

    private final ItemsData itemsData;

    private final OrdersData ordersData;


    public OrdersGeneratorImpl(OrdersData ordersData) {
        this.ordersData = ordersData;
        customerData = ordersData.getCustomerData();
        itemsData = ordersData.getItemsData();
    }

    @Override
    public Mono<OrderSaveRequest> generateRandomSaveOrderRequest(Max max) {
        return Mono.defer(() -> {
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            return generateRandomItems(threadLocalRandom, max).collectList().flatMap(items ->
                    Mono.zip(getRandomPurchaseMethod(threadLocalRandom), generateRandomCustomer(threadLocalRandom)).map(tuple -> OrderSaveRequest.builder()
                            .couponUsed(ordersData.getCouponUsedValues().get(threadLocalRandom.nextInt(ordersData.getCouponUsedValues().size())))
                            .purchaseMethod(tuple.getT1().getValue())
                            .storeLocation(ordersData.getStoreLocationValues().get(threadLocalRandom.nextInt(ordersData.getStoreLocationValues().size())))
                            .customer(tuple.getT2())
                            .items(items)
                            .build())

            );
        });
    }

    private Mono<PurchaseMethod> getRandomPurchaseMethod(ThreadLocalRandom threadLocalRandom) {
        return Mono.just(Arrays.stream(
                        PurchaseMethod.values()).filter(pm -> pm.getValue()
                        .equals(ordersData.getPurchaseMethodValues()
                                .get(threadLocalRandom.nextInt(ordersData.getPurchaseMethodValues()
                                        .size()))))
                .findFirst().orElse(PurchaseMethod.IN_STORE));
    }

    private Mono<Customer> generateRandomCustomer(ThreadLocalRandom threadLocalRandom) {
        return Mono.just(
                Customer.builder()
                        .satisfaction(threadLocalRandom.nextInt(customerData.getSatisfactionRange().getMin(), customerData.getSatisfactionRange().getMax()))
                        .age(threadLocalRandom.nextInt(customerData.getAgeRange().getMin(), customerData.getAgeRange().getMax()))
                        .email(customerData.getEmailValues().get(threadLocalRandom.nextInt(customerData.getEmailValues().size())))
                        .gender(customerData.getGenderValues().get(threadLocalRandom.nextInt(customerData.getGenderValues().size())))
                        .build());
    }

    private Flux<Item> generateRandomItems(ThreadLocalRandom threadLocalRandom, Max max) {
        return Flux.fromStream(Arrays.stream(IntStream.range(0, threadLocalRandom.nextInt(max.getItems()) + 1).toArray()).mapToObj(i -> Item.builder()
                .name(itemsData.getNameValues().get(threadLocalRandom.nextInt(itemsData.getNameValues().size())))
                .price(BigDecimal.valueOf(threadLocalRandom.nextDouble(itemsData.getPriceRange().getMin(), itemsData.getPriceRange()
                        .getMax())).setScale(2, RoundingMode.HALF_UP))
                .tags(getRandomTags(threadLocalRandom, max.getTags()))
                .quantity(threadLocalRandom.nextInt(itemsData.getQuantityRange().getMin(), itemsData.getQuantityRange().getMax()))
                .build()));
    }

    private List<String> getRandomTags(ThreadLocalRandom threadLocalRandom, int maxTagsNumber) {
        var tags = new ArrayList<String>();
        IntStream.range(0, threadLocalRandom.nextInt(maxTagsNumber) + 1).forEach(i ->
                tags.add(ordersData.getItemsData()
                        .getTagsValues()
                        .get(threadLocalRandom.nextInt(ordersData.getItemsData()
                                .getTagsValues()
                                .size()))));
        return tags;
    }
}
