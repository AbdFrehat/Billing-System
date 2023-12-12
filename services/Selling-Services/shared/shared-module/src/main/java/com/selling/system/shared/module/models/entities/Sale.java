package com.selling.system.shared.module.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "order")
public class Sale implements AbstractSale {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "saleDate")
    private Date saleDate;

    @JsonProperty(value = "items")
    private List<Item> items;

    @JsonProperty(value = "storeLocation")
    private String storeLocation;

    @JsonProperty(value = "customer")
    private Customer customer;

    @JsonProperty(value = "couponUsed")
    private boolean couponUsed;

    @JsonProperty(value = "purchaseMethod")
    private String purchaseMethod;

}
