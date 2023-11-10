package com.sales.query.shared.models.service;

import com.sales.query.shared.models.command.QueryCommand;
import org.springframework.data.mongodb.core.query.Query;

public interface QueryBuilderService {

    Query buildQuery(QueryCommand queryCommand);
}
