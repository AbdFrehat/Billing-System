package com.orderizer.data.sync.orders.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
public class Item {

    private String name;

    private List<String> tags;

    private BigDecimal price;

    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (quantity != item.quantity) return false;
        if (!Objects.equals(name, item.name)) return false;
        if (!Objects.equals(tags, item.tags)) return false;
        return Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
