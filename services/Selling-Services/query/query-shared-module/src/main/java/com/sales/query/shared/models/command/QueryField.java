package com.sales.query.shared.models.command;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.selling.shared.models.commons.Range;
import com.selling.shared.models.enums.FieldsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryField {

    private String field;

    @JsonRawValue
    private Object value;

    private FieldsType fieldsType;

    public Object getValue() {
        if (fieldsType.equals(FieldsType.RANGE)) {
            if (value instanceof LinkedHashMap<?, ?> list) {
                return new Range<Object>(list.get("min"), list.get("max"));
            }
        }
        if (fieldsType.equals(FieldsType.LIST)) {
            if (value instanceof LinkedHashMap<?, ?> fieldsMap) {
                return QueryField.builder()
                        .field((String) fieldsMap.get("field"))
                        .value(fieldsMap.get("value"))
                        .fieldsType(FieldsType.valueOf((String) fieldsMap.get("fieldType")))
                        .build();
            }
        }
        return value;
    }
}
