package com.selling.system.data.shared.module.service.interpreter.impl;

import com.selling.system.data.shared.module.service.interpreter.CriteriaExpression;
import com.selling.system.data.shared.module.util.CriteriaBuilderUtil;
import com.selling.system.shared.module.models.commands.QueryField;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;


public class FieldExpression implements CriteriaExpression {

    private final String id;

    public FieldExpression(String id) {
        this.id = id;
    }

    @Override
    public Criteria interpret(Map<String, QueryField> queryFields) {
        return CriteriaBuilderUtil.buildCriteria(queryFields.get(id));
    }

}
