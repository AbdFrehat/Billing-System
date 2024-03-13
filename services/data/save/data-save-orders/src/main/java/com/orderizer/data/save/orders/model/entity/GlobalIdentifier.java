package com.orderizer.data.save.orders.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "globalIdentifiers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalIdentifier {

    private String id;

    private long identifier;
}
