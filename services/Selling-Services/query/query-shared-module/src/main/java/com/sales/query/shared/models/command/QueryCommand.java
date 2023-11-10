package com.sales.query.shared.models.command;

import com.selling.shared.models.commands.QueryCommandAbstract;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QueryCommand implements QueryCommandAbstract {

    private List<QueryField> queryFields;

}


