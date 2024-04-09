package com.orderizer.export.data.json.model.field;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RangeDateField extends Range<LocalDateTime> implements Field {

    private String field;

}
