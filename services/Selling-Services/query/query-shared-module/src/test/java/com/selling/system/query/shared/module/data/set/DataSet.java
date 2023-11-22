package com.selling.system.query.shared.module.data.set;

import com.selling.system.query.shared.module.entites.CustomerDocument;
import com.selling.system.query.shared.module.entites.ItemDocument;
import com.selling.system.query.shared.module.entites.SaleDocument;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commons.Range;
import com.selling.system.shared.module.models.entities.Customer;
import com.selling.system.shared.module.models.entities.Item;
import com.selling.system.shared.module.models.enums.FieldType;
import com.selling.system.shared.module.models.enums.PurchaseMethod;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSet {

    public static final List<ItemDocument> ITEMS;

    public static final List<CustomerDocument> CUSTOMERS;

    public static final List<SaleDocument> SALES;

    public static final Map<String, QueryField> QUERY_FIELD_MAP = new HashMap<>();

    static {
        ITEMS = List.of(
                ItemDocument.builder()
                        .name("Item1")
                        .price(new BigDecimal("1.0"))
                        .tags(List.of("tag1"))
                        .build(),
                ItemDocument.builder()
                        .name("Item2")
                        .price(new BigDecimal("2.0"))
                        .tags(List.of("tag1", "tag2"))
                        .build(),
                ItemDocument.builder()
                        .name("Item3")
                        .price(new BigDecimal("3.0"))
                        .tags(List.of("tag1", "tag2", "tag3"))
                        .build(),
                ItemDocument.builder()
                        .name("Item4")
                        .price(new BigDecimal("4.0"))
                        .tags(List.of("tag1", "tag2", "tag3", "tag4"))
                        .build(),
                ItemDocument.builder()
                        .name("Item5")
                        .price(new BigDecimal("5.0"))
                        .tags(List.of("tag1", "tag2", "tag3", "tag4", "tag5"))
                        .build()
        );
        CUSTOMERS = List.of(
                CustomerDocument.builder()
                        .gender("M")
                        .satisfaction(3)
                        .email("male@male")
                        .age(18)
                        .build(),
                CustomerDocument.builder()
                        .gender("F")
                        .satisfaction(4)
                        .email("female@female")
                        .age(24)
                        .build()
        );
        SALES = List.of(
                SaleDocument.builder()
                        .id("sale1")
                        .items(List.of(ITEMS.get(0), ITEMS.get(1)))
                        .customer(CUSTOMERS.get(0))
                        .storeLocation("Location1")
                        .couponUsed(false)
                        .purchaseMethod(PurchaseMethod.IN_STORE.getValue())
                        .build(),
                SaleDocument.builder()
                        .id("sale2")
                        .items(List.of(ITEMS.get(2), ITEMS.get(3)))
                        .customer(CUSTOMERS.get(1))
                        .storeLocation("Location2")
                        .couponUsed(false)
                        .purchaseMethod(PurchaseMethod.PHONE.getValue())
                        .build(),
                SaleDocument.builder()
                        .id("sale3")
                        .items(List.of(ITEMS.get(4)))
                        .customer(CUSTOMERS.get(1))
                        .storeLocation("Location3")
                        .couponUsed(true)
                        .purchaseMethod(PurchaseMethod.ONLINE.getValue())
                        .build()
        );
        QUERY_FIELD_MAP.put("sale.id", QueryField.builder()
                .field("id")
                .value("sale1")
                .fieldType(FieldType.OTHER)
                .build());
        QUERY_FIELD_MAP.put("sale.items.tags", QueryField.builder()
                .field("items")
                .value(QueryField.builder()
                        .field("tags")
                        .value("tag4")
                        .fieldType(FieldType.STRING)
                        .build())
                .fieldType(FieldType.LIST)
                .build());
        QUERY_FIELD_MAP.put("sale.item.price", QueryField.builder()
                .field("items")
                .value(QueryField.builder()
                        .field("price")
                        .value(new BigDecimal("5.0"))
                        .fieldType(FieldType.OTHER)
                        .build())
                .fieldType(FieldType.LIST)
                .build());
        QUERY_FIELD_MAP.put("sale.item.price.range",
                QueryField.builder()
                        .field("items")
                        .value(QueryField.builder()
                                .field("price")
                                .value(new Range<>(new BigDecimal("3.0"), new BigDecimal("5.0")))
                                .fieldType(FieldType.RANGE)
                                .build())
                        .fieldType(FieldType.LIST)
                        .build());

        QUERY_FIELD_MAP.put("sale.customer.age",
                QueryField.builder()
                        .field("customer.age")
                        .value(new Range<>(23, 24))
                        .fieldType(FieldType.RANGE)
                        .build());
        QUERY_FIELD_MAP.put("sale.storeLocation",
                QueryField.builder()
                        .field("storeLocation")
                        .value("Location2")
                        .fieldType(FieldType.STRING)
                        .build()
        );
    }

}
