package com.orderizer.data.orders.manager.model.request;

import com.orderizer.data.orders.manager.model.common.LocalIdentifierCriteria;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOrdersByLocalIdentifiersRequest {

    private List<LocalIdentifierCriteria> localIdentifierCriteria;

}
