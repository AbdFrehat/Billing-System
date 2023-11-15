package com.selling.sales.kafka.sales.consumer.entities;

import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "sales")
public class SaleDocument extends Sale implements Serializable {

    @Override
    @Id
    public String getId() {
        return super.getId();
    }
}
