package com.orderizer.export.data.json.model.response;

import com.orderizer.export.data.json.model.client.response.OrdersResponse;
import com.orderizer.export.data.json.model.request.ExportOrdersFilterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportOrdersResponse {

    private ExportOrdersFilterRequest filter;

    private OrdersResponse data;
}
