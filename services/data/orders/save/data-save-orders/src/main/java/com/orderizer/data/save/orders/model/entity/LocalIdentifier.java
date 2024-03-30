package com.orderizer.data.save.orders.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "localIdentifiers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalIdentifier {

    @Id
    private String id;

    private String storeLocation;

    private long identifier;
}
