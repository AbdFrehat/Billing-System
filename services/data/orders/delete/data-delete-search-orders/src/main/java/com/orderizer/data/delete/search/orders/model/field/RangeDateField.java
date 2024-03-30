package com.orderizer.data.delete.search.orders.model.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class RangeDateField extends Range<LocalDate> implements Field {

    private String field;

}
