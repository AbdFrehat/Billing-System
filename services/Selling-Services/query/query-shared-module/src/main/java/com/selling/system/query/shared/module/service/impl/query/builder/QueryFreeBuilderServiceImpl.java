package com.selling.system.query.shared.module.service.impl.query.builder;

import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.query.shared.module.service.interpreter.ExpressionBuilder;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.enums.FieldType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.selling.system.query.shared.module.util.CriteriaBuilderUtil.buildTextCriteria;
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
@Service("query-free-builder")
@Slf4j
public class QueryFreeBuilderServiceImpl implements QueryBuilderService {

    private final ExpressionBuilder expressionBuilder;

    public QueryFreeBuilderServiceImpl(ExpressionBuilder expressionBuilder) {
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
        query.addCriteria(buildTextCriteria(queryCommand.getQueryFields().get(FieldType.FREE.name())));
        log.info("ExpressionBuilder: {}", expressionBuilder);
        if(!queryCommand.isCount()) {
            addPageable(queryCommand, query);
            addSorting(queryCommand, query);
        }
        query.fields().exclude(queryCommand.getExcludedFields());
        return query;
    }

}
