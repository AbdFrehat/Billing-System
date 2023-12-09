package com.selling.system.data.sales.get.service;

import com.selling.system.data.shared.module.service.QueryBuilderService;
import com.selling.system.data.shared.module.service.interpreter.ExpressionBuilder;
import com.selling.system.data.shared.module.util.CriteriaBuilderUtil;
import com.selling.system.shared.module.models.commands.DataCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.selling.system.data.shared.module.util.QueryBuilderUtil.addPageable;
import static com.selling.system.data.shared.module.util.QueryBuilderUtil.addSorting;

/**
 * The class QueryBuilderServiceImpl implements QueryBuilderService interface which is used to
 * generate queries using buildQuery method and criteria by buildCriteria.
 * The generated object from them are used in mongo template to select the affected documents for
 * querying, updating and deletion.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@Primary
@Service
@Slf4j
public class QueryBuilderServiceImpl implements QueryBuilderService {

    private final ExpressionBuilder expressionBuilder;

    public QueryBuilderServiceImpl(ExpressionBuilder expressionBuilder) {
        this.expressionBuilder = expressionBuilder;
    }

    /**
     * This method is used to build the {@link Query} object based on the passed {@link DataCommand} object.
     *
     * @param dataCommand: {@link DataCommand} contains the list of fields that the query searches on.
     * @return returns query: {@link Query} object to be used with mongoTemplate for searching and updating operations.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Query buildQuery(DataCommand dataCommand) {
        var query = new Query();
        dataCommand.getQueryFields().forEach((s, queryField) -> {
            query.addCriteria(CriteriaBuilderUtil.buildCriteria(queryField));
        });
        if (!dataCommand.isCount()) {
            addPageable(dataCommand, query);
            addSorting(dataCommand, query);
        }
        if (dataCommand.getExcludedFields() != null && dataCommand.getExcludedFields().length > 0) {
            query.fields().exclude(dataCommand.getExcludedFields());
        }
        return query;
    }


}
