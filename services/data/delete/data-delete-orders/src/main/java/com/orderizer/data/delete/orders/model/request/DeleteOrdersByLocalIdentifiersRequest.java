package com.orderizer.data.delete.orders.model.request;

import com.orderizer.data.delete.orders.model.LocalIdentifierCriteria;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOrdersByLocalIdentifiersRequest {

    private List<LocalIdentifierCriteria> localIdentifierCriteria;

}
