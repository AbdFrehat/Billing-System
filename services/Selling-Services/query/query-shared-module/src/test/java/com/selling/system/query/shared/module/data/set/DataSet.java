package com.selling.system.query.shared.module.data.set;

import com.selling.system.query.shared.module.entites.Customer;
import com.selling.system.query.shared.module.entites.Item;
import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.shared.module.models.enums.PurchaseMethod;

import java.math.BigDecimal;
import java.util.List;

public class DataSet {

    public static final List<Item> ITEMS;

    public static final List<Customer> CUSTOMERS;

    public static final List<Sale> SALES;

    static {
        ITEMS = List.of(
                Item.builder()
                        .name("Item1")
                        .price(new BigDecimal("1.0"))
                        .tags(List.of("tag1"))
                        .build(),
                Item.builder()
                        .name("Item2")
                        .price(new BigDecimal("2.0"))
                        .tags(List.of("tag1", "tag2"))
                        .build(),
                Item.builder()
                        .name("Item3")
                        .price(new BigDecimal("3.0"))
                        .tags(List.of("tag1", "tag2", "tag3"))
                        .build(),
                Item.builder()
                        .name("Item4")
                        .price(new BigDecimal("4.0"))
                        .tags(List.of("tag1", "tag2", "tag3", "tag4"))
                        .build(),
                Item.builder()
                        .name("Item5")
                        .price(new BigDecimal("5.0"))
                        .tags(List.of("tag1", "tag2", "tag3", "tag4", "tag5"))
                        .build()
        );
        CUSTOMERS = List.of(
                Customer.builder()
                        .gender("M")
                        .satisfaction(3)
                        .email("male@male")
                        .age(18)
                        .build(),
                Customer.builder()
                        .gender("F")
                        .satisfaction(4)
                        .email("female@female")
                        .age(24)
                        .build()
        );
        SALES = List.of(
                Sale.builder()
                        .id("sale1")
                        .items(List.of(ITEMS.get(0), ITEMS.get(1)))
                        .customer(CUSTOMERS.get(0))
                        .storeLocation("Location1")
                        .couponUsed(false)
                        .purchaseMethod(PurchaseMethod.IN_STORE)
                        .build(),
                Sale.builder()
                        .id("sale2")
                        .items(List.of(ITEMS.get(2), ITEMS.get(3)))
                        .customer(CUSTOMERS.get(1))
                        .storeLocation("Location2")
                        .couponUsed(false)
                        .purchaseMethod(PurchaseMethod.PHONE)
                        .build(),
                Sale.builder()
                        .id("sale3")
                        .items(List.of(ITEMS.get(4)))
                        .customer(CUSTOMERS.get(1))
                        .storeLocation("Location3")
                        .couponUsed(true)
                        .purchaseMethod(PurchaseMethod.ONLINE)
                        .build()
        );
    }

}
