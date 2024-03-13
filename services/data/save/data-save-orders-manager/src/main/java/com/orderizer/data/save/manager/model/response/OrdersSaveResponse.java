package com.orderizer.data.save.manager.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersSaveResponse {

    private List<OrderSaveResponse> orders;
}
