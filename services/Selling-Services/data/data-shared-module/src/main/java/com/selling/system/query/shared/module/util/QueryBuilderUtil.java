package com.selling.system.query.shared.module.util;

import com.selling.system.shared.module.models.commands.QueryCommand;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

public class QueryBuilderUtil {

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
    public static void addPageable(QueryCommand queryCommand, Query query) {
        var page = queryCommand.getPage();
        var size = queryCommand.getSize();
        if (page != 0 || size != 0)
            query.with(PageRequest.of(page, size));
    }

    public static void addSorting(QueryCommand queryCommand, Query query) {
        var sortField = queryCommand.getSortField();
        if (sortField != null) {
            query.with(Sort.by(Sort.Direction.valueOf(sortField.getDirection()), sortField.getField()));
        }
    }

}
