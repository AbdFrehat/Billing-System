package com.orderizer.data.stores.manager.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    private String id;

    private int identifier;

    private String storeLocation;
}
