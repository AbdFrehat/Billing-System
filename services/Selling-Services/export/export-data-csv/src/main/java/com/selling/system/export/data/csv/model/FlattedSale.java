package com.selling.system.export.data.csv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlattedSale {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "saleDate")
    private String saleDate;

    @JsonProperty(value = "storeLocation")
    private String storeLocation;

    @JsonProperty(value = "customer.gender")
    private String customerGender;

    @JsonProperty(value = "customer.age")
    private int customerAge;

    @JsonProperty(value = "customer.email")
    private String customerEmail;

    @JsonProperty(value = "customer.satisfaction")
    private int customerSatisfaction;

    @JsonProperty(value = "couponUsed")
    private boolean couponUsed;

    @JsonProperty(value = "purchaseMethod")
    private String purchaseMethod;

    @JsonProperty(value = "item.name")
    private String itemName;

    @JsonProperty(value = "item.tags")
    private String itemTags;

    @JsonProperty(value = "item.price")
    private BigDecimal itemPrice;

    @JsonProperty(value = "item.quantity")
    private int itemQuantity;
}
