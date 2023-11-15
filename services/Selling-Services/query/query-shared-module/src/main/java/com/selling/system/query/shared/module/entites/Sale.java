package com.selling.system.query.shared.module.entites;

import com.selling.system.shared.module.models.entities.AbstractSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale implements AbstractSale {

    @Id
    private String id;

    private Date saleDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;

}
