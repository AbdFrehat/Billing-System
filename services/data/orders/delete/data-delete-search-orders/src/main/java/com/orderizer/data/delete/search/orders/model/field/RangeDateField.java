package com.orderizer.data.delete.search.orders.model.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class RangeDateField extends Range<LocalDateTime> implements Field {

    private String field;

}
