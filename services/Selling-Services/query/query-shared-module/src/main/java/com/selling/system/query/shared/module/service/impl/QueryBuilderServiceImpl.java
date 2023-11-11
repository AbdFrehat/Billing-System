package com.selling.system.query.shared.module.service.impl;

import com.selling.system.shared.module.models.command.QueryCommand;
import com.selling.system.shared.module.models.command.QueryField;
import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.shared.module.models.commons.Range;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * The class QueryBuilderServiceImpl implements QueryBuilderService interface which is used to
 * generate queries using buildQuery method and criteria by buildCriteria.
 * The generated object from them are used in mongo template to select the affected documents for
 * querying, updating and deletion.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {


    /**
     * @param queryCommand: {@link QueryCommand} contains the list of fields that the query searches on.
     * @return returns query: {@link Query} object to be used with mongoTemplate for searching and updating operations.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Query buildQuery(QueryCommand queryCommand) {
        Query query = new Query();
        for (QueryField queryField : queryCommand.getQueryFields())
            query.addCriteria(buildCriteria(queryField));

        return query;
    }

    /**
     * @param queryField: {@link QueryField} contains the needed information to create a Criteria from it:
     *                    <ul>
     *                    <li>Filed: Represents the field document to search on</li>
     *                    <li>Value: Represents the value of field to match by it</li>
     *                    <li>FieldType: To create different criteria based on the field type</li></li>
     *                    </ul>
     * @return criteria: {@link Criteria} which will be used by buildQuery Function to create the query object
     * * @author Abd Frehat
     * * @since 1.0
     */

    private Criteria buildCriteria(QueryField queryField) {
        Criteria criteria = new Criteria();
        switch (queryField.getFieldType()) {
            case OTHER -> criteria = Criteria.where(queryField.getField()).is(queryField.getValue());
            case STRING -> criteria = Criteria.where(queryField.getField()).regex((String) queryField.getValue());
            case RANGE -> {
                if (queryField.getValue() instanceof Range<?> range) {
                    criteria = Criteria.where(queryField.getField()).lte(range.getMax()).gte(range.getMin());
                }
            }
            case LIST -> {
                if (queryField.getValue() instanceof QueryField) {
                    criteria = Criteria.where(queryField.getField()).elemMatch(buildCriteria((QueryField) queryField.getValue()));
                }
            }
        }
        return criteria;
    }

}
