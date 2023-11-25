package com.selling.system.query.shared.module.service.interpreter.impl.operatprs;

import com.selling.system.query.shared.module.service.interpreter.CriteriaExpression;
import com.selling.system.shared.module.models.commands.QueryField;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;

public class AndExpression implements CriteriaExpression {

    private final CriteriaExpression criteriaExpression1;

    private final CriteriaExpression criteriaExpression2;

    public AndExpression(CriteriaExpression criteriaExpression1, CriteriaExpression criteriaExpression2) {
        this.criteriaExpression1 = criteriaExpression1;
        this.criteriaExpression2 = criteriaExpression2;
    }

    @Override
    public Criteria interpret(Map<String, QueryField> queryFields) {
        return new Criteria().andOperator(criteriaExpression1.interpret(queryFields), criteriaExpression2.interpret(queryFields));
    }
}
