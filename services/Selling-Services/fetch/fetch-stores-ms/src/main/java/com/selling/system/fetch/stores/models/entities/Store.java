package com.selling.system.fetch.stores.models.entities;

import com.selling.system.shared.module.models.entities.AbstractStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stores")
public class Store implements AbstractStore {
    
    @Id
    private String id;

    private String storeLocation;
}
