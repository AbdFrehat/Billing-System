package com.selling.system.shared.module.models.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.selling.system.shared.module.convertors.ObjectToObjectsConvertor;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import com.selling.system.shared.module.models.commons.Range;
import com.selling.system.shared.module.models.enums.FieldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryField implements QueryFieldAbstract, Serializable {

    @NotBlank(message = "QueryField.field can not be empty.")
    @JsonProperty(value = "field")
    private String field;

//    @JsonRawValue
    @JsonProperty(value = "value")
    @NotNull(message = "QueryField.value can not be null")
    private Object value;

    @NotNull(message = "QueryField.fieldType can not be null")
    @JsonProperty(value = "fieldType")
    @ValidFieldTypeEnum(message = "QueryField.fieldType is not supported", regexp = "RANGE|STRING|OTHER|LIST")
    private FieldType fieldType;

    public Object getValue() {
        if (fieldType.equals(FieldType.RANGE) && !(this.value instanceof Range<?>)) {
            this.value = ObjectToObjectsConvertor.toRange(this.value);
            return this.value;
        }
        if (fieldType.equals(FieldType.LIST) && !(this.value instanceof QueryField)) {
            this.value = ObjectToObjectsConvertor.toQueryFiled(value);
            return this.value;
        }
        return this.value;
    }
}
