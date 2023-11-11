package com.selling.system.shared.module.models.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QueryCommand implements QueryCommandAbstract {

    private List<@Valid QueryField> queryFields;

    @Min(value = 0, message = "Page number must be greater than 0 and less than " + Integer.MAX_VALUE)
    private int page = 0;

    @Min(value = 0, message = "Size number must be greater than 0 and less than " + Integer.MAX_VALUE)
    private int size = 0;

}


