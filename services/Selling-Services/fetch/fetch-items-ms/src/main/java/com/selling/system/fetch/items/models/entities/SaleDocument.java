package com.selling.system.fetch.items.models.entities;

import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "sales")
public class SaleDocument extends Sale {


    @Override
    @Id
    public String getId() {
        return super.getId();
    }
}
