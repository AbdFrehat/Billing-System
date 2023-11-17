package com.selling.system.query.shared.module.service.impl;

import com.selling.system.query.shared.module.service.QueryBuilderService;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
import com.selling.system.shared.module.models.commons.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

import static com.selling.system.shared.module.utils.DateUtil.convertStringToDate;

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
     * This method is used to build the {@link Query} object based on the passed {@link QueryCommand} object.
     *
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
        this.addPageable(queryCommand, query);
        this.addSorting(queryCommand, query);
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
        int page = queryCommand.getPage();
        int size = queryCommand.getSize();
        if (page != 0 || size != 0)
            query.with(PageRequest.of(page, size));
    }

    private void addSorting(QueryCommand queryCommand, Query query) {
        SortField sortField = queryCommand.getSortField();
        if (sortField != null) {
            query.with(Sort.by(Sort.Direction.valueOf(sortField.getDirection()), sortField.getField()));
        }
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
            case DATE -> {
                criteria = Criteria.where(queryField.getField())
                        .is(convertStringToDate((String) queryField.getValue(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
            }
            case RANGE_DATE -> {
                if (queryField.getValue() instanceof Range<?> range) {
                    criteria = Criteria.where(queryField.getField())
                            .lte(convertStringToDate((String) range.getMax(), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                            .gte(convertStringToDate((String) range.getMin(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                }
            }
        }
        return criteria;
    }

}
