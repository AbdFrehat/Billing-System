package com.orderizer.data.get.orders.manager.model.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RangeField extends Range<Long> implements Field {

    private String field;

}
