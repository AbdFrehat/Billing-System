package com.selling.system.query.shared.module.service.impl.query.builder;

import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.query.shared.module.service.interpreter.ExpressionBuilder;
import com.selling.system.shared.module.models.commands.QueryCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.selling.system.query.shared.module.util.QueryBuilderUtil.addPageable;
import static com.selling.system.query.shared.module.util.QueryBuilderUtil.addSorting;

/**
 * The class QueryBuilderServiceImpl implements QueryBuilderService interface which is used to
 * generate queries using buildQuery method and criteria by buildCriteria.
 * The generated object from them are used in mongo template to select the affected documents for
 * querying, updating and deletion.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@Service("query-opt-builder")
@Slf4j
public class QueryOptBuilderServiceImpl implements QueryBuilderService {

    private final ExpressionBuilder expressionBuilder;

    public QueryOptBuilderServiceImpl(ExpressionBuilder expressionBuilder) {
        this.expressionBuilder = expressionBuilder;
    }

    /**
     * This method is used to build the {@link Query} object based on the passed {@link QueryCommand} object.
     *
     * @param queryCommand: {@link QueryCommand} contains the list of fields that the query searches on.
     * @return returns query: {@link Query} object to be used with mongoTemplate for searching and updating operations.
     * @author Abd Frehat
     * @since 1.0
     */
    @Override
    public Query buildQuery(QueryCommand queryCommand) {
        var query = new Query();
        var criteriaExpression = expressionBuilder.build(queryCommand.getExpression());
        query.addCriteria(criteriaExpression.interpret(queryCommand.getQueryFields()));
        log.info("ExpressionBuilder: {}", expressionBuilder);
        if(!queryCommand.isCount()) {
            addPageable(queryCommand, query);
            addSorting(queryCommand, query);
        }
        query.fields().exclude(queryCommand.getExcludedFields());
        return query;
    }

}
