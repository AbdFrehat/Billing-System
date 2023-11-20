package com.selling.system.query.shared.module.util;

import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commons.Range;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

import static com.selling.system.shared.module.utils.DateUtil.convertStringToDate;

@Service
public class CriteriaBuilderUtil{

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
    public static Criteria buildCriteria(QueryField queryField) {
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
