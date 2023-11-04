package com.sale.source.sales.source.ms.service;

import com.sale.source.sales.source.ms.model.data.CustomerData;
import com.sale.source.sales.source.ms.model.data.ItemsData;
import com.sale.source.sales.source.ms.model.data.SalesData;
import com.sale.source.sales.source.ms.model.entities.Customer;
import com.sale.source.sales.source.ms.model.entities.Item;
import com.sale.source.sales.source.ms.model.entities.Sale;
import com.selling.shared.models.enums.PurchaseMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class SaleGeneratorServiceImpl implements SaleGeneratorService {

    @Value("${emit.max.items}")
    private int maxItemsNumber;

    @Value("${emit.max.tags}")
    private int maxTagsNumber;

    List<String> tags = new ArrayList<>();

    List<Item> items = new ArrayList<>();

    private CustomerData customerData;

    private ItemsData itemsData;

    private final SalesData salesData;

    private final Random random;

    public SaleGeneratorServiceImpl(SalesData salesData) {
        this.salesData = salesData;
        random = new Random();
        customerData = salesData.getCustomerData();
        itemsData = salesData.getItemsData();
    }

    @Override
    public Sale generateRandomSale() {
        return Sale.builder()
                .saleDate(new Date())
                .couponUsed(salesData.getCouponUsedValues().get(random.nextInt(salesData.getCouponUsedValues().size())))
                .purchaseMethod(getRandomPurchaseMethod())
                .storeLocation(salesData.getStoreLocationValues().get(random.nextInt(salesData.getStoreLocationValues().size())))
                .customer(getRandomCustomer()
                        .build())
                .items(generateRandomItems())
                .build();
    }

    private PurchaseMethod getRandomPurchaseMethod() {
        Optional<PurchaseMethod> purchaseMethod = Arrays.stream(
                PurchaseMethod.values()).filter(pm -> pm.getValue().equals(
                salesData.getPurchaseMethodValues().get(random.nextInt(salesData.getPurchaseMethodValues().size())))).findFirst();
        return purchaseMethod.orElse(PurchaseMethod.IN_STORE);
    }

    private Customer.CustomerBuilder getRandomCustomer() {
        return Customer.builder()
                .satisfaction(random.nextInt(customerData.getSatisfactionRange().getMin(), customerData.getSatisfactionRange().getMax()))
                .age(random.nextInt(customerData.getAgeRange().getMin(), customerData.getAgeRange().getMax()))
                .email(customerData.getEmailValues().get(random.nextInt(customerData.getEmailValues().size())))
                .gender(customerData.getGenderValues().get(random.nextInt(customerData.getGenderValues().size())));
    }

    private List<Item> generateRandomItems() {
        items.clear();
        IntStream.range(0,  random.nextInt(this.maxItemsNumber) + 1).forEach(i ->
                items.add(Item.builder()
                        .name(itemsData.getNameValues().get(random.nextInt(itemsData.getNameValues().size())))
                        .price(BigDecimal.valueOf(random.nextDouble(itemsData.getPriceRange().getMin(), itemsData.getPriceRange().getMax())).setScale(2, RoundingMode.HALF_UP))
                        .tags(getRandomTags())
                        .quantity(random.nextInt(itemsData.getQuantityRange().getMin(), itemsData.getQuantityRange().getMax()))
                        .build())
        );
        return items;
    }

    private List<String> getRandomTags() {
        tags.clear();
        IntStream.range(0, random.nextInt(this.maxTagsNumber) + 1).forEach(i ->
                tags.add(salesData.getItemsData().getTagsValues().get(random.nextInt(salesData.getItemsData().getTagsValues().size()))));
        return tags;
    }
}
