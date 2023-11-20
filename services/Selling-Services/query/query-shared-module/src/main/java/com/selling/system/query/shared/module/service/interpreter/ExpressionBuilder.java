package com.selling.system.query.shared.module.service.interpreter;

import org.springframework.stereotype.Service;

public interface ExpressionBuilder {

    public CriteriaExpression build(String expression);

}
