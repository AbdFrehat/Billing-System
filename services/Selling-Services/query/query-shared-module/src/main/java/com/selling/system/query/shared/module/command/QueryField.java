package com.selling.system.query.shared.module.command;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.selling.system.query.shared.module.convertors.ObjectToObjectsConvertor;
import com.selling.system.shared.models.commands.QueryFieldAbstract;
import com.selling.system.shared.models.enums.FieldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryField implements QueryFieldAbstract {

    @NotBlank(message = "QueryField.field can not be empty.")
    private String field;

    @JsonRawValue
    @NotNull(message = "QueryField.value can not be null")
    private Object value;

    @NotNull(message = "QueryField.fieldType can not be null")
    private FieldType fieldType;

    public Object getValue() {
        if (fieldType.equals(FieldType.RANGE)) {
            return ObjectToObjectsConvertor.toRange(this.value);
        }
        if (fieldType.equals(FieldType.LIST)) {
            return ObjectToObjectsConvertor.toQueryFiled(value);
        }
        return value;
    }
}
