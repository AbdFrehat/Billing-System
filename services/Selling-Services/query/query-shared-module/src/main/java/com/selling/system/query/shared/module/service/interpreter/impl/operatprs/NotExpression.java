package com.selling.system.query.shared.module.service.interpreter.impl.operatprs;

import com.selling.system.query.shared.module.service.interpreter.CriteriaExpression;
import com.selling.system.shared.module.models.commands.QueryField;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;

public class NotExpression implements CriteriaExpression {

    private final CriteriaExpression criteriaExpression;

    public NotExpression(CriteriaExpression criteriaExpression) {
        this.criteriaExpression = criteriaExpression;
    }

    @Override
    public Criteria interpret(Map<String, QueryField> queryFields) {
        return new Criteria().norOperator(criteriaExpression.interpret(queryFields));
    }
}
