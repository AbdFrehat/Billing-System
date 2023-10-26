package com.sales.fetch.items.models.entities;

import com.selling.shared.models.entities.AbstractSale;
import com.selling.shared.models.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
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
public class Sale implements AbstractSale {
    @Id
    private String id;
    private Date saleDate;
    private List<Item> items;
    private String storeLocation;
    private Customer customer;
    private boolean couponUsed;
    private PurchaseMethod purchaseMethod;
}
