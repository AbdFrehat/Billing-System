package com.orderizer.data.save.orders.manager.validator.impl;

import com.orderizer.data.save.orders.manager.model.common.Item;
import com.orderizer.data.save.orders.manager.model.request.OrderSaveRequest;
import com.orderizer.data.save.orders.manager.validator.api.Validator;
import com.orderizer.core.exceptions.Technical.RequestBodyInvalidException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderSaveRequestValidator implements Validator<OrderSaveRequest> {
    @Override
    public Mono<OrderSaveRequest> validate(OrderSaveRequest order) {
        return Mono.defer(() -> {
            if (StringUtils.isBlank(order.getStoreLocation())) {
                return Mono.error(new RequestBodyInvalidException("StoreLocation cannot be empty."));
            }
            if (StringUtils.isBlank(order.getPurchaseMethod())) {
                return Mono.error(new RequestBodyInvalidException("PurchaseMethod cannot be empty."));
            }
            if (ObjectUtils.isEmpty(order.getCustomer())) {
                return Mono.error(new RequestBodyInvalidException("Customer cannot be empty."));
            }
            if (StringUtils.isBlank(order.getCustomer().getEmail())) {
                return Mono.error(new RequestBodyInvalidException("Email cannot be empty."));
            }
            if (StringUtils.isBlank(order.getCustomer().getGender())) {
                return Mono.error(new RequestBodyInvalidException("Gender cannot be empty."));
            }
            if (order.getCustomer().getAge() < 0) {
                return Mono.error(new RequestBodyInvalidException("Age cannot be negative."));
            }
            if (ObjectUtils.isEmpty(order.getItems()) || order.getItems().isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("Items cannot be empty."));
            }
            if(order.getCustomer().getSatisfaction() < 0 || order.getCustomer().getSatisfaction() > 5) {
                return Mono.error(new RequestBodyInvalidException("Satisfaction must be between 0 and 5."));
            }
            List<Item> itemsWithEmptyName = order.getItems().stream()
                    .filter(item -> StringUtils.isEmpty(item.getName())).toList();
            if (!itemsWithEmptyName.isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("One or more items has an empty name."));
            }
            List<Item> itemsWithInvalidPrice = order.getItems().stream()
                    .filter(item -> item.getPrice().compareTo(BigDecimal.ZERO) < 0).toList();
            if (!itemsWithInvalidPrice.isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("One or more items has a negative price."));
            }
            return Mono.just(order);
        });
    }

}
