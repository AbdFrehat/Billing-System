package com.selling.system.shared.module.models.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import com.selling.system.shared.module.models.enums.QueryMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryCommand implements QueryCommandAbstract {

    @JsonProperty(value = "queryFields")
    private List<@Valid QueryField> queryFields;

    @JsonProperty(value = "page")
    @Min(value = 0, message = "Page number must be greater than 0 and less than " + Integer.MAX_VALUE)
    private int page = 0;

    @JsonProperty(value = "size")
    @Min(value = 0, message = "Size number must be greater than 0 and less than " + Integer.MAX_VALUE)
    private int size = 0;

    @NotNull(message = "QueryCommand.queryMethod can not be null")
    @JsonProperty(value = "queryMethod")
    @ValidFieldTypeEnum(message = "QueryCommand.queryMethod is not supported", regexp = "GET_SALES|SAVE_SALE|SAVE_SALES|UPDATE_SALE|DELETE_SALE|DELETE_SALES")
    private QueryMethod queryMethod;

//    @JsonRawValue
    @JsonProperty(value = "payload")
    private Object payload;

}


