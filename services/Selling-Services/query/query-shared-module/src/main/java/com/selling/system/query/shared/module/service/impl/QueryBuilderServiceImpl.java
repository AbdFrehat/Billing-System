package com.selling.system.query.shared.module.service.impl;

import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.query.shared.module.service.interpreter.ExpressionBuilder;
import com.selling.system.shared.module.models.commands.QueryCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@Slf4j
public class QueryBuilderServiceImpl implements QueryBuilderService {

    private final ExpressionBuilder expressionBuilder;

    public QueryBuilderServiceImpl(ExpressionBuilder expressionBuilder) {
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
        this.addPageable(queryCommand, query);
        this.addSorting(queryCommand, query);
        query.fields().exclude(queryCommand.getExcludedFields());
        return query;
    }

    /**
     * This method is used to paginate the response or not, it depends on the size and page number values.
     * If both are zero that means the client does not care about them and in that case all documents will be
     * retrieved.
     *
     * @param queryCommand {@link QueryCommand} which is used to get the page and size values.
     * @param query        {@link Query} which is used to add the {@link PageRequest} object to the {@link Query} one.
     * @author Abd Frehat
     * @since 1.0
     */
    private void addPageable(QueryCommand queryCommand, Query query) {
        var page = queryCommand.getPage();
        var size = queryCommand.getSize();
        if (page != 0 || size != 0)
            query.with(PageRequest.of(page, size));
    }

    private void addSorting(QueryCommand queryCommand, Query query) {
        var sortField = queryCommand.getSortField();
        if (sortField != null) {
            query.with(Sort.by(Sort.Direction.valueOf(sortField.getDirection()), sortField.getField()));
        }
    }


}
