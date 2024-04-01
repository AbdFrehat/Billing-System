package com.orderizer.data.sync.orders.model.entity.elastic;

import com.orderizer.data.sync.orders.model.common.Customer;
import com.orderizer.data.sync.orders.model.common.Item;
import com.orderizer.data.sync.orders.model.entity.mongo.MongoOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;

@Data
@Document(indexName = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticOrder {

    @Id
    private String id;

    private long globalIdentifier;

    private long localIdentifier;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private ZonedDateTime orderDate;

    private List<Item> items;

    private BigDecimal totalPrice;

    private String storeLocation;

    private Customer customer;

    private boolean couponUsed;

    private String purchaseMethod;

    public boolean equals(MongoOrder mongoOrder) {
        if (mongoOrder == null) return false;
        if (globalIdentifier != mongoOrder.getGlobalIdentifier()) return false;
        if (localIdentifier != mongoOrder.getLocalIdentifier()) return false;
        if (couponUsed != mongoOrder.isCouponUsed()) return false;
        if (!Objects.equals(id, mongoOrder.getId())) return false;
        if (!Objects.equals(orderDate, mongoOrder.getOrderDate().atZone(UTC))) return false;
//        if (!Objects.equals(items, mongoOrder.getItems())) return false;
        if (!Objects.equals(totalPrice.stripTrailingZeros(), mongoOrder.getTotalPrice().stripTrailingZeros()))
            return false;
        if (!(mongoOrder.getStoreLocation().equals(this.getStoreLocation()))) return false;
        if (!Objects.equals(customer, mongoOrder.getCustomer())) return false;
        return mongoOrder.getPurchaseMethod().equals(this.getPurchaseMethod());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (globalIdentifier ^ (globalIdentifier >>> 32));
        result = 31 * result + (int) (localIdentifier ^ (localIdentifier >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (storeLocation != null ? storeLocation.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (couponUsed ? 1 : 0);
        result = 31 * result + (purchaseMethod != null ? purchaseMethod.hashCode() : 0);
        return result;
    }
}
