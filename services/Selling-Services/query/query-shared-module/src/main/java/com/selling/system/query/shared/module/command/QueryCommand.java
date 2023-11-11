package com.selling.system.query.shared.module.command;

import com.selling.system.shared.module.models.commands.QueryCommandAbstract;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QueryCommand implements QueryCommandAbstract {

    private List<@Valid QueryField> queryFields;

}


