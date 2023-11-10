package com.sales.query.shared.models.service;

import com.sales.query.shared.models.command.QueryCommand;
import org.springframework.data.mongodb.core.query.Query;

/**
 * The QueryBuilderService interface is used to
 * generate queries using buildQuery method and criteria by buildCriteria.
 * The generated object from them are used in mongo template to select the affected documents for
 * querying, updating and deletion.
 *
 * @author Abd Frehat
 * @since 1.0
 */
public interface QueryBuilderService {

    /**
     * @param queryCommand: {@link QueryCommand} contains the list of filed that the query searches on.
     * @return returns query: {@link Query} object to be used with mongoTemplate for searching and updating operations.
     * @author Abd Frehat
     * @since 1.0
     */
    Query buildQuery(QueryCommand queryCommand);
}
