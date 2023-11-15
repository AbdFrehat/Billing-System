package com.selling.system.query.shared.module.entites;

import com.selling.system.shared.module.models.entities.AbstractSale;
import com.selling.system.shared.module.models.entities.Customer;
import com.selling.system.shared.module.models.entities.Item;
import com.selling.system.shared.module.models.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sales")
public class SaleDocument implements AbstractSale {

    @Id
    private String id;

    private Date saleDate;

    private List<Item> items;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;
}
