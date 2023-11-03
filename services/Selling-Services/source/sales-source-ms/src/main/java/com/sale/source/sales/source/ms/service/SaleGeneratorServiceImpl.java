package com.sale.source.sales.source.ms.service;

import com.sale.source.sales.source.ms.model.data.SalesData;
import com.sale.source.sales.source.ms.model.entities.Customer;
import com.sale.source.sales.source.ms.model.entities.Item;
import com.sale.source.sales.source.ms.model.entities.Sale;
import com.selling.shared.models.enums.PurchaseMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class SaleGeneratorServiceImpl implements SaleGeneratorService {

    private SalesData salesData;

    private Random random;

    public SaleGeneratorServiceImpl(SalesData salesData) {
        this.salesData = salesData;
        random = new Random();
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
                .satisfaction(random.nextInt(salesData.getCustomerData().getSatisfactionRange().getMin(), salesData.getCustomerData().getSatisfactionRange().getMax()))
                .age(random.nextInt(salesData.getCustomerData().getAgeRange().getMin(), salesData.getCustomerData().getAgeRange().getMax()))
                .email(salesData.getCustomerData().getEmailValues().get(random.nextInt(salesData.getCustomerData().getEmailValues().size())))
                .gender(
                        salesData.getCustomerData().getGenderValues().get(random.nextInt(salesData.getCustomerData().getGenderValues().size()))
                );
    }

    private List<Item> generateRandomItems() {
        List<Item> items = new ArrayList<>();
        int randomNumber = random.nextInt(3);
        IntStream.range(0, randomNumber + 1).forEach(i ->
                items.add(Item.builder()
                        .name(salesData.getItemsData().getNameValues().get(random.nextInt(salesData.getItemsData().getNameValues().size())))
                        .price(BigDecimal.valueOf(random.nextDouble(salesData.getItemsData().getPriceRange().getMin(), salesData.getItemsData().getPriceRange().getMax())).setScale(2, RoundingMode.HALF_UP))
                        .tags(getRandomTags())
                        .quantity(random.nextInt(salesData.getItemsData().getQuantityRange().getMin(), salesData.getItemsData().getQuantityRange().getMax()))
                        .build())
        );
        return items;
    }

    private List<String> getRandomTags() {
        List<String> tags = new ArrayList<>();
        int randomNumber = random.nextInt(3);
        IntStream.range(0, randomNumber + 1).forEach(i ->
                tags.add(salesData.getItemsData().getTagsValues().get(random.nextInt(salesData.getItemsData().getTagsValues().size()))));
        return tags;
    }
}
