package com.selling.system.fetch.customer.models.entites;

import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "sales")
public class SaleDocument extends Sale {

    @Override
    @Id
    public String getId() {
        return null;
    }
}
