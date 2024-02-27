package com.selling.system.source.random.generator.sales.service;


import com.selling.system.shared.module.models.entities.Customer;
import com.selling.system.shared.module.models.entities.Item;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.PurchaseMethod;
import com.selling.system.source.random.generator.sales.model.data.CustomerData;
import com.selling.system.source.random.generator.sales.model.data.ItemsData;
import com.selling.system.source.random.generator.sales.model.data.SalesData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class SaleGeneratorServiceImpl implements SaleGeneratorService {

    @Value("${emit.max.items}")
    private int maxItemsNumber;

    @Value("${emit.max.tags}")
    private int maxTagsNumber;

    private final CustomerData customerData;

    private final ItemsData itemsData;

    private final SalesData salesData;


    public SaleGeneratorServiceImpl(SalesData salesData) {
        this.salesData = salesData;
        customerData = salesData.getCustomerData();
        itemsData = salesData.getItemsData();
    }

    @Override
    public Sale generateRandomSale() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return Sale.builder()
                .saleDate(new Date(System.currentTimeMillis()))
                .couponUsed(salesData.getCouponUsedValues().get(threadLocalRandom.nextInt(salesData.getCouponUsedValues().size())))
                .purchaseMethod(getRandomPurchaseMethod(threadLocalRandom).getValue())
                .storeLocation(salesData.getStoreLocationValues().get(threadLocalRandom.nextInt(salesData.getStoreLocationValues().size())))
                .customer(getRandomCustomer(threadLocalRandom)
                        .build())
                .items(generateRandomItems(threadLocalRandom))
                .build();
    }

    private PurchaseMethod getRandomPurchaseMethod(ThreadLocalRandom threadLocalRandom) {
        Optional<PurchaseMethod> purchaseMethod = Arrays.stream(
                PurchaseMethod.values()).filter(pm -> pm.getValue().equals(
                salesData.getPurchaseMethodValues().get(threadLocalRandom.nextInt(salesData.getPurchaseMethodValues().size())))).findFirst();
        return purchaseMethod.orElse(PurchaseMethod.IN_STORE);
    }

    private Customer.CustomerBuilder getRandomCustomer(ThreadLocalRandom threadLocalRandom) {
        return Customer.builder()
                .satisfaction(threadLocalRandom.nextInt(customerData.getSatisfactionRange().getMin(), customerData.getSatisfactionRange().getMax()))
                .age(threadLocalRandom.nextInt(customerData.getAgeRange().getMin(), customerData.getAgeRange().getMax()))
                .email(customerData.getEmailValues().get(threadLocalRandom.nextInt(customerData.getEmailValues().size())))
                .gender(customerData.getGenderValues().get(threadLocalRandom.nextInt(customerData.getGenderValues().size())));
    }

    private List<Item> generateRandomItems(ThreadLocalRandom threadLocalRandom) {
        List<Item> items = new ArrayList<>();
        IntStream.range(0, threadLocalRandom.nextInt(this.maxItemsNumber) + 1).forEach(i ->
                items.add(Item.builder()
                        .name(itemsData.getNameValues().get(threadLocalRandom.nextInt(itemsData.getNameValues().size())))
                        .price(BigDecimal.valueOf(threadLocalRandom.nextDouble(itemsData.getPriceRange().getMin(), itemsData.getPriceRange().getMax())).setScale(2, RoundingMode.HALF_UP))
                        .tags(getRandomTags(threadLocalRandom))
                        .quantity(threadLocalRandom.nextInt(itemsData.getQuantityRange().getMin(), itemsData.getQuantityRange().getMax()))
                        .build())
        );
        return items;
    }

    private List<String> getRandomTags(ThreadLocalRandom threadLocalRandom) {
        List<String> tags = new ArrayList<>();
        IntStream.range(0, threadLocalRandom.nextInt(this.maxTagsNumber) + 1).forEach(i ->
                tags.add(salesData.getItemsData().getTagsValues().get(threadLocalRandom.nextInt(salesData.getItemsData().getTagsValues().size()))));
        return tags;
    }
}
