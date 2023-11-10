package com.sales.query.shared.models.service.impl;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.command.QueryField;
import com.sales.query.shared.models.service.QueryBuilderService;
import com.selling.shared.models.commons.Range;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {

    @Override
    public Query buildQuery(QueryCommand queryCommand) {
        Query query = new Query();
        List<QueryField> queryFields = queryCommand.getQueryFields();
        if (!queryFields.isEmpty()) {
            for (QueryField queryField : queryFields)
                query.addCriteria(buildCriteria(queryField));

        }
        return query;
    }

    private Criteria buildCriteria(QueryField queryField) {
        Criteria criteria = new Criteria();
        switch (queryField.getFieldsType()) {
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
