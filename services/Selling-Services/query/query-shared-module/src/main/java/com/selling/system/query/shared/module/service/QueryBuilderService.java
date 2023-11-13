package com.selling.system.query.shared.module.service;

import com.selling.system.shared.module.models.commands.QueryCommand;
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
     * This method is used to build the {@link Query} object based on the passed {@link QueryCommand} object.
     *
     * @param queryCommand: {@link QueryCommand} contains the list of filed that the query searches on.
     * @return returns query: {@link Query} object to be used with mongoTemplate for searching and updating operations.
     * @author Abd Frehat
     * @since 1.0
     */
    Query buildQuery(QueryCommand queryCommand);
}
