package com.orderizer.data.save.order.validator.impl;

import com.orderizer.data.save.order.exception.RequestBodyInvalidException;
import com.orderizer.data.save.order.model.entity.Item;
import com.orderizer.data.save.order.model.request.OrderSaveRequest;
import com.orderizer.data.save.order.validator.api.Validator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderSaveRequestValidator implements Validator<OrderSaveRequest> {
    @Override
    public Mono<OrderSaveRequest> validate(OrderSaveRequest orderSaveRequest) {
        return Mono.defer(() -> {
            if (StringUtils.isBlank(orderSaveRequest.getStoreLocation())) {
                return Mono.error(new RequestBodyInvalidException("StoreLocation cannot be empty."));
            }
            if (StringUtils.isBlank(orderSaveRequest.getPurchaseMethod())) {
                return Mono.error(new RequestBodyInvalidException("PurchaseMethod cannot be empty."));
            }
            if (ObjectUtils.isEmpty(orderSaveRequest.getCustomer())) {
                return Mono.error(new RequestBodyInvalidException("Customer cannot be empty."));
            }
            if (StringUtils.isBlank(orderSaveRequest.getCustomer().getEmail())) {
                return Mono.error(new RequestBodyInvalidException("Email cannot be empty."));
            }
            if (StringUtils.isBlank(orderSaveRequest.getCustomer().getGender())) {
                return Mono.error(new RequestBodyInvalidException("Gender cannot be empty."));
            }
            if (orderSaveRequest.getCustomer().getAge() < 0) {
                return Mono.error(new RequestBodyInvalidException("Age cannot be negative."));
            }
            if (ObjectUtils.isEmpty(orderSaveRequest.getItems()) || orderSaveRequest.getItems().isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("Items cannot be empty."));
            }
            List<Item> itemsWithEmptyName = orderSaveRequest.getItems().stream()
                    .filter(item -> StringUtils.isEmpty(item.getName())).toList();
            if (!itemsWithEmptyName.isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("One or more items has an empty name."));
            }
            List<Item> itemsWithInvalidPrice = orderSaveRequest.getItems().stream()
                    .filter(item -> item.getPrice().compareTo(BigDecimal.ZERO) < 0).toList();
            if (!itemsWithInvalidPrice.isEmpty()) {
                return Mono.error(new RequestBodyInvalidException("One or more items has a negative price."));
            }
            return Mono.just(orderSaveRequest);
        });
    }

}
