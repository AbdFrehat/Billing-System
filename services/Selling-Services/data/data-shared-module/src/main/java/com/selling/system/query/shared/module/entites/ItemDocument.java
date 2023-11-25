package com.selling.system.query.shared.module.entites;

import com.selling.system.shared.module.models.entities.AbstractItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDocument implements AbstractItem {

    @TextIndexed
    private String name;

    @TextIndexed
    private List<String> tags;

    private BigDecimal price;

    private int quantity;
}
