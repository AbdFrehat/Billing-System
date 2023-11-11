package com.selling.system.query.shared.module.command;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.selling.system.query.shared.module.convertors.ObjectToObjectsConvertor;
import com.selling.system.shared.models.commands.QueryFieldAbstract;
import com.selling.system.shared.models.enums.FieldsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryField implements QueryFieldAbstract {

    private String field;

    @JsonRawValue
    private Object value;

    private FieldsType fieldsType;

    public Object getValue() {
        if (fieldsType.equals(FieldsType.RANGE)) {
            return ObjectToObjectsConvertor.toRange(this.value);
        }
        if (fieldsType.equals(FieldsType.LIST)) {
            return ObjectToObjectsConvertor.toQueryFiled(value);
        }
        return value;
    }
}
