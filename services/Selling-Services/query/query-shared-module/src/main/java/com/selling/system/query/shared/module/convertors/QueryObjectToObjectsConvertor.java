package com.selling.system.query.shared.module.convertors;

import com.selling.system.query.shared.module.entites.CustomerDocument;
import com.selling.system.query.shared.module.entites.ItemDocument;
import com.selling.system.query.shared.module.entites.SaleDocument;
import com.selling.system.shared.module.models.exceptions.PayloadBadFormatException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class QueryObjectToObjectsConvertor {

    public static CustomerDocument toCustomer(Object object) {
        if (object instanceof LinkedHashMap<?, ?> list) {
            try {
                return CustomerDocument.builder()
                        .age((int) ((LinkedHashMap<?, ?>) list.get("customer")).get("age"))
                        .satisfaction((Integer) ((LinkedHashMap<?, ?>) list.get("customer")).get("satisfaction"))
                        .gender((String) ((LinkedHashMap<?, ?>) list.get("customer")).get("gender"))
                        .email((String) ((LinkedHashMap<?, ?>) list.get("customer")).get("email"))
                        .build();
            } catch (Exception e) {
                throw new PayloadBadFormatException("Unable to parse the customer part of the payload ");
            }
        } else {
            throw new PayloadBadFormatException("Unable to parse the customer part of the payload ");
        }
    }

    public static List<ItemDocument> toItems(List<?> items) {
        try {
            return items.stream().map(item -> (LinkedHashMap<?, ?>) item).map(item -> ItemDocument.builder()
                    .name((String) item.get("name"))
                    .price(BigDecimal.valueOf((double) item.get("price")))
                    .quantity((int) item.get("quantity"))
                    .tags((List<String>) item.get("tags"))
                    .build()).toList();
        } catch (Exception e) {
            throw new PayloadBadFormatException("Unable to parse the items part of the payload ");
        }
    }

    public static SaleDocument toSale(Object object) {
        if (object instanceof LinkedHashMap<?, ?> list) {
            try {
                return SaleDocument.builder()
                        .id((String) list.get("id"))
                        .couponUsed((boolean) list.get("couponUsed"))
                        .saleDate(Date.from(OffsetDateTime.parse((String) list.get("saleDate"), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()))
                        .purchaseMethod((String) list.get("purchaseMethod"))
                        .customer(toCustomer(list))
                        .storeLocation((String) list.get("storeLocation"))
                        .items(toItems((List<?>) list.get("items")))
                        .build();
            } catch (Exception e) {
                throw new PayloadBadFormatException("Unable to parse the sale part of the payload ");
            }
        } else {
            throw new PayloadBadFormatException("Unable to parse the sale part of the payload ");
        }
    }

    public static List<SaleDocument> toSales(Object object) {
        if (object instanceof List<?> list) {
            try {
                return list.stream().map(QueryObjectToObjectsConvertor::toSale).toList();
            } catch (Exception e) {
                throw new PayloadBadFormatException("Unable to parse the customer part of the payload ");
            }
        } else {
            throw new PayloadBadFormatException("Unable to parse the sale part of the payload ");
        }
    }


}