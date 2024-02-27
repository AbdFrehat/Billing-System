package com.selling.system.data.shared.module.service.impl;

import com.selling.system.data.shared.module.service.QueryBuilderService;
import com.selling.system.shared.module.models.commands.DataCommand;
import lombok.extern.slf4j.Slf4j;
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
public class DummyQueryBuilderServiceImpl implements QueryBuilderService {

    @Override
    public Query buildQuery(DataCommand dataCommand) {
        return null;
    }


}
